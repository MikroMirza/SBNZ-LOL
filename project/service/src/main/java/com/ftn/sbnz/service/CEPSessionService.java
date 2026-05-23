package com.ftn.sbnz.service;

import com.ftn.sbnz.model.*;
import com.ftn.sbnz.model.events.*;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.EntryPoint;
import org.kie.api.time.SessionPseudoClock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class CEPSessionService {

    private KieSession kSession;
    private EntryPoint championStream;
    private SessionPseudoClock clock;

    private int lastEnemyCs = 0;
    private boolean lastEnemyDead = false;

    public CEPSessionService(KieContainer kieContainer) {
        this.kSession = kieContainer.newKieSession("LOLSession");
        this.clock = kSession.getSessionClock();
        this.championStream = kSession.getEntryPoint("ChampionStream");
    }

    public List<Recommendation> processTick(GameState state, int tickSeconds) {
        clock.advanceTime(tickSeconds, TimeUnit.SECONDS);

        Champion our   = state.getOurMid();
        Champion enemy = state.getEnemyMid();
        long now = clock.getCurrentTime();

        if (our.getHpPercent() < 30) {
            championStream.insert(new LowHpEvent(now, "our"));
        }

        if (enemy.getHpPercent() < 40) {
            championStream.insert(new LowHpEvent(now, "enemy"));
        }

        if (state.getEnemyJungler().getLastSeenTimer() < 20) {
            championStream.insert(new JunglerSpottedEvent(now));
        }

        if (enemy.getCurrentPosition() != Position.MIDLANE) {
            championStream.insert(new EnemyGoneEvent(now));
        }

        if (enemy.getCsScore() > lastEnemyCs) {
            championStream.insert(new EnemyKilledMinionEvent(now));
            lastEnemyCs = enemy.getCsScore();
        }

        if (enemy.isDead() && !lastEnemyDead) {
            championStream.insert(new DamageEvent(now, "enemy_dead", 0));
        }
        lastEnemyDead = enemy.isDead();

        kSession.insert(state);
        kSession.insert(our);
        kSession.insert(enemy);

        kSession.fireAllRules();

        List<Recommendation> results = new ArrayList<>(
            kSession.getObjects(o -> o instanceof Recommendation)
                .stream()
                .map(o -> (Recommendation) o)
                .collect(Collectors.toList())
        );
        results.forEach(r -> kSession.delete(kSession.getFactHandle(r)));

        return results;
    }

    public void close() {
        if (kSession != null) kSession.dispose();
    }
}