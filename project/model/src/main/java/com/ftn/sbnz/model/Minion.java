package com.ftn.sbnz.model;


public class Minion extends Character {
 
    public enum MinionType { MELEE, RANGED, SIEGE }
 
    private MinionType minionType;
    private int goldValue;
 
    public Minion() {}
 
    public Minion(MinionType minionType, int[] position) {
        super(
            minionType == MinionType.SIEGE ? 1400 : (minionType == MinionType.RANGED ? 500 : 500),
            0,
            position,
            minionType == MinionType.SIEGE ? 200 : 330,
            0,
            0,
            1,
            minionType == MinionType.RANGED ? 600 : 150,
            true
        );
        this.minionType = minionType;
        this.goldValue = minionType == MinionType.SIEGE ? 90 :
                         minionType == MinionType.RANGED ? 22 : 21;
    }
 
    public MinionType getMinionType() { return minionType; }
    public void setMinionType(MinionType minionType) { this.minionType = minionType; }
 
    public int getGoldValue() { return goldValue; }
    public void setGoldValue(int goldValue) { this.goldValue = goldValue; }
 
    @Override
    public String toString() {
        return String.format("[Minion:%s HP:%d Gold:%d]", minionType, getHp(), goldValue);
    }
}