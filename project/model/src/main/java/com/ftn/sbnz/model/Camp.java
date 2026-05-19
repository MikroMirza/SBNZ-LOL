package com.ftn.sbnz.model;

public class Camp {
 
    private int[] position; 
    private int value;
    private int respawn;
 
    public Camp() {}
 
    public Camp(int[] position, int value, int respawn) {
        this.position = position;
        this.value = value;
        this.respawn = respawn;
    }
 
    public int[] getPosition() { return position; }
    public void setPosition(int[] position) { this.position = position; }
 
    public int getValue() { return value; }
    public void setValue(int value) { this.value = value; }
 
    public int getRespawn() { return respawn; }
    public void setRespawn(int respawn) { this.respawn = respawn; }
}
 