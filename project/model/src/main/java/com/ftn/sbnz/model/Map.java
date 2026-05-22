package com.ftn.sbnz.model;

public class Map {

    public enum Lane { TOP, MID, BOT, JUNGLE }

    private int timer;
    private Lane[] lanes;
    private int[] size;
    private float speed;
    private int minionSpawnTimer;

    public Map() {
        this.timer = 0;
        this.lanes = Lane.values();
        this.size = new int[]{128, 128};
        this.speed = 1.0f;
        this.minionSpawnTimer = 30;
    }

    public int getTimer() { return timer; }
    public void setTimer(int timer) { this.timer = timer; }

    public Lane[] getLanes() { return lanes; }
    public void setLanes(Lane[] lanes) { this.lanes = lanes; }

    public int[] getSize() { return size; }
    public void setSize(int[] size) { this.size = size; }

    public float getSpeed() { return speed; }
    public void setSpeed(float speed) { this.speed = speed; }

    public int getMinionSpawn() { return minionSpawnTimer; }
    public void setMinionSpawn(int minionSpawn) { this.minionSpawnTimer = minionSpawn; }

    public String getFormattedTimer() {
        int minutes = timer / 60;
        int seconds = timer % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}