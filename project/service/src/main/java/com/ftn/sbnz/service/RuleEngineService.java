package com.ftn.sbnz.service;


import com.ftn.sbnz.model.*;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
 
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
 
public class RuleEngineService {
 
    private final KieContainer kieContainer;
 
    public RuleEngineService() {
        KieServices ks = KieServices.Factory.get();
        this.kieContainer = ks.getKieClasspathContainer();
    }
 

    public List<Recommendation> evaluate(GameState state, PlayerState playerState) {
        KieSession kSession = kieContainer.newKieSession("LOLSession");
 
        try {
            kSession.insert(state);
            kSession.insert(state.getOurMid());
            kSession.insert(state.getEnemyMid());
            kSession.insert(state.getEnemyJungler());
            kSession.insert(state.getEnemySupport());
            kSession.insert(state.getDragon());
            kSession.insert(playerState);
 
            kSession.fireAllRules();
 
            List<Recommendation> recommendations = new ArrayList<>(
                kSession.getObjects(o -> o instanceof Recommendation)
                    .stream()
                    .map(o -> (Recommendation) o)
                    .collect(Collectors.toList())
            );
 
            recommendations.forEach(r -> r.setTimestamp(state.getTimer()));
 
            recommendations.sort(Comparator.comparingInt(Recommendation::getPriority).reversed());
 
            return recommendations;
 
        } finally {
            kSession.dispose();
        }
    }
 

    public Recommendation getTopRecommendation(GameState state, PlayerState playerState) {
        List<Recommendation> all = evaluate(state, playerState);
        return all.stream()
            .filter(r -> r.getPriority() >= playerState.getInterruptThreshold())
            .findFirst()
            .orElse(null);
    }
}
 