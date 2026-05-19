package com.ftn.sbnz.service;


import com.ftn.sbnz.model.*;
import com.ftn.sbnz.model.Champion.PlayerType;
import com.ftn.sbnz.model.Champion.ChampionType;
import com.ftn.sbnz.model.GameState.WaveState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
 
public class SimulationService {
 
    private static final int GAME_DURATION = 840;
    private static final int TICK_INTERVAL = 5;
 
    private final RuleEngineService ruleEngine;
    private final Random random = new Random();
 
    public SimulationService() {
        this.ruleEngine = new RuleEngineService();
    }
 
    public void runSimulation() {
        System.out.println("--- LOL MID LANE SIMULATION ---\n");
 
        Champion ourMid = createRandomChampion(true);
        Champion enemyMid = createRandomChampion(false);
        Champion enemyJungler = createJungler();
        Champion enemySupport = createSupport();
 
        GameState state = new GameState(ourMid, enemyMid, enemyJungler, enemySupport);
        PlayerState playerState = new PlayerState();
 
        System.out.printf("Our Mid:    %s%n", ourMid);
        System.out.printf("Enemy Mid:  %s%n", enemyMid);
        System.out.println("\nSimulation starting...\n");
        System.out.println("-".repeat(60));
 
        List<Recommendation> log = new ArrayList<>();
 
        for (int t = 0; t <= GAME_DURATION; t++) {
            state.setTimer(t);
            state.getDragon().setTimer(state.getDragon().getTimer() - 1);
 
            updateGameState(state, t);
 
            tickCooldowns(ourMid, enemyMid);
            playerState.tick();
 
            if (t % TICK_INTERVAL == 0) {
                Recommendation top = ruleEngine.getTopRecommendation(state, playerState);
 
                if (top != null) {
                    boolean transitioned = playerState.tryTransition(
                        top.getSuggestedAction(), top.getPriority()
                    );
 
                    if (transitioned || top.getPriority() >= 3) {
                        System.out.printf("[%s] [P%d] %s%n",
                            state.getFormattedTimer(), top.getPriority(), top.getMessage());
                        log.add(top);
                    }
                }
            }
        }
 
        printFinalReport(ourMid, enemyMid, log);
    }
 
    private void updateGameState(GameState state, int t) {
        Champion our = state.getOurMid();
        Champion enemy = state.getEnemyMid();
 
        our.setGold(our.getGold() + 0.4);
        enemy.setGold(enemy.getGold() + 0.35); 

        if (t % 30 == 0 && t > 0) {
            our.setCsScore(our.getCsScore() + random.nextInt(5) + 3);
            enemy.setCsScore(enemy.getCsScore() + random.nextInt(4) + 2);
            our.setGold(our.getGold() + (our.getCsScore() * 0.5));
        }
 
        if (t == 70)  { our.setLevel(2); enemy.setLevel(2); }
        if (t == 150) { our.setLevel(3); enemy.setLevel(3); }
        if (t == 240) { our.setLevel(4); enemy.setLevel(4); }
        if (t == 330) { our.setLevel(5); enemy.setLevel(5); }
        if (t == 420) {
            our.setLevel(6); enemy.setLevel(6);
            our.setUltCooldown(0);
            System.out.println("\n[07:00] *** Both champions hit level 6 - ULTIMATES UNLOCKED ***\n");
        }
 
        our.setPowerMult(1.0f + (our.getLevel() * 0.1f) + (float)(our.getGold() / 10000));
        enemy.setPowerMult(1.0f + (enemy.getLevel() * 0.1f) + (float)(enemy.getGold() / 10000));
 
        if (t % 45 == 0) {
            WaveState[] states = WaveState.values();
            state.setWaveState(states[random.nextInt(states.length)]);
        }
 
        if (state.getDragon().getTimer() <= 0) {
            state.getDragon().setTimer(300);
            state.getDragon().setAlive(true);
            System.out.printf("%n[%s] *** DRAGON SPAWNED ***%n%n", state.getFormattedTimer());
        }
 
        if (t % 20 == 0 && random.nextInt(3) == 0) {
            int ourDmg = random.nextInt(15) + 5;
            int enemyDmg = random.nextInt(15) + 5;
            our.setHpPercent(Math.max(10, our.getHpPercent() - ourDmg));
            enemy.setHpPercent(Math.max(10, enemy.getHpPercent() - enemyDmg));
        }
 
        if (t % 60 == 0) {
            our.setHpPercent(Math.min(100, our.getHpPercent() + 20));
            our.setManaPercent(Math.min(100, our.getManaPercent() + 20));
        }
 

        if (enemy.isDead()) {
            enemy.setRespawnTimer(enemy.getRespawnTimer() - 1);
            if (enemy.getRespawnTimer() <= 0) {
                enemy.setDead(false);
                enemy.setHpPercent(100);
                enemy.setCurrentPosition(Champion.Position.MIDLANE);
            }
        }
 
        state.getEnemyJungler().setLastSeenTimer(
            state.getEnemyJungler().getLastSeenTimer() + 1
        );
    }
 
    private void tickCooldowns(Champion our, Champion enemy) {
        if (our.getCooldownInt() > 0) our.setCooldownInt(our.getCooldownInt() - 1);
        if (our.getUltCooldown() > 0) our.setUltCooldown(our.getUltCooldown() - 1);
        if (enemy.getCooldownInt() > 0) enemy.setCooldownInt(enemy.getCooldownInt() - 1);
        if (enemy.getUltCooldown() > 0) enemy.setUltCooldown(enemy.getUltCooldown() - 1);
    }
 
    private Champion createRandomChampion(boolean isOurs) {
        PlayerType[] types = PlayerType.values();
        ChampionType[] champTypes = ChampionType.values();
        PlayerType pt = types[random.nextInt(types.length)];
        ChampionType ct = champTypes[random.nextInt(champTypes.length)];
        boolean ranged = ct == ChampionType.MAGE;
 
        System.out.printf("Selected %s: PlayerType=%s ChampionType=%s%n",
            isOurs ? "OUR champion" : "ENEMY champion", pt, ct);
 
        return new Champion(pt, ct, ranged);
    }
 
    private Champion createJungler() {
        Champion jg = new Champion(PlayerType.AGGRESSIVE, ChampionType.FIGHTER, false);
        jg.setCurrentPosition(Champion.Position.UNKNOWN);
        jg.setLastSeenTimer(25);
        return jg;
    }
 
    private Champion createSupport() {
        Champion sup = new Champion(PlayerType.BALANCED, ChampionType.MAGE, true);
        sup.setLastSeenTimer(15);
        sup.setLastSeenHp(80);
        return sup;
    }
 
    private void printFinalReport(Champion our, Champion enemy,
                                  List<Recommendation> log) {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("           FINAL REPORT - 14 MINUTE MARK");
        System.out.println("═".repeat(60));
        System.out.printf("Our Mid    | HP:%d%% | Gold:%.0f | CS:%d | Kills:%d | Deaths:%d%n",
            our.getHpPercent(), our.getGold(), our.getCsScore(), our.getKills(), our.getDeaths());
        System.out.printf("Enemy Mid  | HP:%d%% | Gold:%.0f | CS:%d | Kills:%d | Deaths:%d%n",
            enemy.getHpPercent(), enemy.getGold(), enemy.getCsScore(), enemy.getKills(), enemy.getDeaths());
 
        System.out.println("\nTop recommendations fired during game:");
        log.stream()
            .filter(r -> r.getPriority() >= 3)
            .limit(10)
            .forEach(r -> System.out.printf("  [P%d][%s] %s%n",
                r.getPriority(), r.getCategory(), r.getMessage()));
 
        System.out.println("\nPlayer type: " + our.getPlayerType());
        System.out.println("Simulation complete.");
    }
}