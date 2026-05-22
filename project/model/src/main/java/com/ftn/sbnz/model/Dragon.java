package com.ftn.sbnz.model;

import java.util.List;

public class Dragon extends Character {

    public enum TeamInControl { ALLY, ENEMY, NONE }
    private boolean isAlive;
    private int timer;
    private TeamInControl teamInControl;
    private int overallAmp;

    

    public Dragon() {
        super(
            3400,
            0,
            new int[]{40, 60},
            0,
            0,
            0,
            6,
            175,
            true
        );
        this.isAlive = false;
        this.timer = 300;
        this.teamInControl = TeamInControl.NONE;
        this.overallAmp = 0;
    }

    public Dragon(List<Champion> champs, int time){
        int dragonLevel = 6;
        int level = 0;
        for(Champion c: champs){
            level += c.getLevel();
        }
        if(level/champs.size()>6){
            dragonLevel = level/champs.size();
        }
        int additionalHpMultiplier = (time - 300)/60;

        this.setHp(3400 + (240*additionalHpMultiplier));
        this.setValue(0);
        this.setPosition(new int[]{40, 60});
        this.setSpeed(0);
        this.setMana(0);
        this.setAp(0);
        this.setLevel(dragonLevel);
        this.setRange(175);
        this.setVisible(true);
        this.isAlive = false;
        this.timer = 300;
        this.teamInControl = TeamInControl.NONE;
        this.overallAmp = 0;
    }



    public boolean isAlive() { return isAlive; }
    public void setAlive(boolean alive) { isAlive = alive; }

    public int getTimer() { return timer; }
    public void setTimer(int timer) { this.timer = timer; }

    public TeamInControl getTeamInControl() { return teamInControl; }
    public void setTeamInControl(TeamInControl teamInControl) { this.teamInControl = teamInControl; }

    public int getOverallAmp() { return overallAmp; }
    public void setOverallAmp(int overallAmp) { this.overallAmp = overallAmp; }
    
    @Override
    public String toString() {
        return String.format("[Dragon | Alive:%s Timer:%ds Control:%s Amp:%d%%]",
                isAlive, timer, teamInControl, overallAmp);
    }
}