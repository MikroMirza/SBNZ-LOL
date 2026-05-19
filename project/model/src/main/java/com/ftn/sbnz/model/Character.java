package com.ftn.sbnz.model;

public abstract class Character {

    private int hp;
    private int value;
    private int[] position;
    private int speed;
    private int ap;
    private int mana;
    private int level;
    private int range;
    private boolean visible;

    public Character() {}

    public Character(int hp, int value, int[] position, int speed, int ap, int mana, int level, int range, boolean visible) {
        this.hp = hp;
        this.value = value;
        this.position = position;
        this.speed = speed;
        this.ap = ap;
        this.mana = mana;
        this.level = level;
        this.range = range;
        this.visible = visible;
    }

    public int getHp() { return hp; }
    public void setHp(int hp) { this.hp = hp; }

    public int getValue() { return value; }
    public void setValue(int value) { this.value = value; }

    public int[] getPosition() { return position; }
    public void setPosition(int[] position) { this.position = position; }

    public int getSpeed() { return speed; }
    public void setSpeed(int speed) { this.speed = speed; }

    public int getAp() { return ap; }
    public void setAp(int ap) { this.ap = ap; }

    public int getMana() { return mana; }
    public void setMana(int mana) { this.mana = mana; }

    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }

    public int getRange() { return range; }
    public void setRange(int range) { this.range = range; }

    public boolean isVisible() { return visible; }
    public void setVisible(boolean visible) { this.visible = visible; }
}