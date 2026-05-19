package com.ftn.sbnz.model;

public class Fountain {

    private int healing;
    private int range;
    private int speedBonus;

    public Fountain() {
        this.healing = 100;
        this.range = 1000;
        this.speedBonus = 150;
    }

    public int getHealing() { return healing; }
    public void setHealing(int healing) { this.healing = healing; }

    public int getRange() { return range; }
    public void setRange(int range) { this.range = range; }

    public int getSpeedBonus() { return speedBonus; }
    public void setSpeedBonus(int speedBonus) { this.speedBonus = speedBonus; }
}