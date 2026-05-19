package com.ftn.sbnz.model;

/**
 * Interrupt table:
 *   LANING    → interrupted by priority >= 2
 *   ROAMING   → interrupted by priority >= 4
 *   RECALLING → interrupted by priority = 5 only
 *   FIGHTING  → interrupted by priority = 5 only
 */
public class PlayerState {


    private ActionState currentState;
    private int stateTimer;
    private int currentPriority;
    private String lastRecommendation;

    public PlayerState() {
        this.currentState = ActionState.LANING;
        this.stateTimer = 0;
        this.currentPriority = 1;
        this.lastRecommendation = "Game started - focus on CS";
    }

    public int getInterruptThreshold() {
        switch (currentState) {
            case LANING:    return 2;
            case ROAMING:   return 4;
            case RECALLING: return 5;
            case FIGHTING:  return 5;
            case BACKING:   return 3;
            default:        return 2;
        }
    }

    public boolean tryTransition(ActionState newState, int newPriority) {
        if (newPriority >= getInterruptThreshold()) {
            this.currentState = newState;
            this.currentPriority = newPriority;
            this.stateTimer = 0;
            return true;
        }
        return false;
    }

    public void tick() { this.stateTimer++; }

    public ActionState getCurrentState() { return currentState; }
    public void setCurrentState(ActionState currentState) { this.currentState = currentState; }

    public int getStateTimer() { return stateTimer; }
    public void setStateTimer(int stateTimer) { this.stateTimer = stateTimer; }

    public int getCurrentPriority() { return currentPriority; }
    public void setCurrentPriority(int p) { this.currentPriority = p; }

    public String getLastRecommendation() { return lastRecommendation; }
    public void setLastRecommendation(String lastRecommendation) { this.lastRecommendation = lastRecommendation; }

    @Override
    public String toString() {
        return String.format("PlayerState[%s | %ds in state | P%d]",
                currentState, stateTimer, currentPriority);
    }
}