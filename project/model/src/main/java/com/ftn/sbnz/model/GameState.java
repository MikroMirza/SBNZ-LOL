package com.ftn.sbnz.model;

public class GameState {
 
    public enum WaveState {
        PUSHED_TO_ENEMY_TOWER,
        PUSHED_TO_OUR_TOWER,
        NEUTRAL,
        FREEZING_NEAR_OUR_TOWER,
        FREEZING_NEAR_ENEMY_TOWER
    }
 
    public static final double POWERSPIKE_EARLY = 1300;
    public static final double POWERSPIKE_MID   = 2200;
    public static final double POWERSPIKE_LATE  = 3400;
 
    private int timer;
    private Map map;
    private Dragon dragon;
    private Jungle jungle;
    private Fountain fountain;
 
    private WaveState waveState;
    private int minionCount;
 
    private int teamNearDragon;
    private int enemyTeamNearDragon;
 
    private Champion ourMid;
    private Champion enemyMid;
    private Champion enemyJungler;
    private Champion enemySupport;
 
    private int allyBotHpPercent;
    private int enemyBotHpPercent;
    private int ourBotSideNumbers;
    private int enemyBotSideNumbers;
    private int allyTopHpPercent;
 
    public GameState() {}
 
    public GameState(Champion ourMid, Champion enemyMid,
                     Champion enemyJungler, Champion enemySupport) {
        this.ourMid = ourMid;
        this.enemyMid = enemyMid;
        this.enemyJungler = enemyJungler;
        this.enemySupport = enemySupport;
        this.timer = 0;
        this.map = new Map();
        this.dragon = new Dragon();
        this.jungle = new Jungle();
        this.fountain = new Fountain();
        this.waveState = WaveState.NEUTRAL;
        this.minionCount = 6;
        this.teamNearDragon = 0;
        this.enemyTeamNearDragon = 0;
        this.allyBotHpPercent = 100;
        this.enemyBotHpPercent = 100;
        this.ourBotSideNumbers = 2;
        this.enemyBotSideNumbers = 2;
        this.allyTopHpPercent = 100;
    }
 
    public String getFormattedTimer() {
        return String.format("%02d:%02d", timer / 60, timer % 60);
    }
 
    public int getTimer() { return timer; }
    public void setTimer(int timer) { this.timer = timer; }
 
    public Map getMap() { return map; }
    public void setMap(Map map) { this.map = map; }
 
    public Dragon getDragon() { return dragon; }
    public void setDragon(Dragon dragon) { this.dragon = dragon; }
 
    public Jungle getJungle() { return jungle; }
    public void setJungle(Jungle jungle) { this.jungle = jungle; }
 
    public Fountain getFountain() { return fountain; }
    public void setFountain(Fountain fountain) { this.fountain = fountain; }
 
    public WaveState getWaveState() { return waveState; }
    public void setWaveState(WaveState waveState) { this.waveState = waveState; }
 
    public int getMinionCount() { return minionCount; }
    public void setMinionCount(int minionCount) { this.minionCount = minionCount; }
 
    public int getTeamNearDragon() { return teamNearDragon; }
    public void setTeamNearDragon(int teamNearDragon) { this.teamNearDragon = teamNearDragon; }
 
    public int getEnemyTeamNearDragon() { return enemyTeamNearDragon; }
    public void setEnemyTeamNearDragon(int n) { this.enemyTeamNearDragon = n; }
 
    public Champion getOurMid() { return ourMid; }
    public void setOurMid(Champion ourMid) { this.ourMid = ourMid; }
 
    public Champion getEnemyMid() { return enemyMid; }
    public void setEnemyMid(Champion enemyMid) { this.enemyMid = enemyMid; }
 
    public Champion getEnemyJungler() { return enemyJungler; }
    public void setEnemyJungler(Champion enemyJungler) { this.enemyJungler = enemyJungler; }
 
    public Champion getEnemySupport() { return enemySupport; }
    public void setEnemySupport(Champion enemySupport) { this.enemySupport = enemySupport; }
 
    public int getAllyBotHpPercent() { return allyBotHpPercent; }
    public void setAllyBotHpPercent(int allyBotHpPercent) { this.allyBotHpPercent = allyBotHpPercent; }
 
    public int getEnemyBotHpPercent() { return enemyBotHpPercent; }
    public void setEnemyBotHpPercent(int enemyBotHpPercent) { this.enemyBotHpPercent = enemyBotHpPercent; }
 
    public int getOurBotSideNumbers() { return ourBotSideNumbers; }
    public void setOurBotSideNumbers(int n) { this.ourBotSideNumbers = n; }
 
    public int getEnemyBotSideNumbers() { return enemyBotSideNumbers; }
    public void setEnemyBotSideNumbers(int n) { this.enemyBotSideNumbers = n; }
 
    public int getAllyTopHpPercent() { return allyTopHpPercent; }
    public void setAllyTopHpPercent(int allyTopHpPercent) { this.allyTopHpPercent = allyTopHpPercent; }
 
    @Override
    public String toString() {
        return String.format("GameState[%s | Wave:%s | Dragon:%ds | Team@Dragon:%d vs %d]",
                getFormattedTimer(), waveState,
                dragon.getTimer(), teamNearDragon, enemyTeamNearDragon);
    }
}