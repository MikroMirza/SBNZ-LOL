package com.ftn.sbnz.model;

public class Minion extends Character {

    public enum MinionType { MELEE, RANGED, SIEGE }

    private MinionType minionType;
    private int goldValue;

    public Minion() {}

    public Minion(MinionType minionType, int[] position) {
        super(
            hpFor(minionType),
            0,
            position,
            speedFor(minionType),
            0,
            0,
            1,
            rangeFor(minionType),
            true
        );
        this.minionType = minionType;
        this.goldValue = goldFor(minionType);
    }
    
    private static int hpFor(MinionType type) {
        switch (type) {
            case MELEE:  return 475;
            case RANGED: return 300;
            case SIEGE:  return 1500;
            default:     return 300;
        }
    }

    private static int speedFor(MinionType type) {
        switch (type) {
            case SIEGE: return 300;
            default:    return 325;
        }
    }

    private static int rangeFor(MinionType type) {
        switch (type) {
            case MELEE:  return 100;
            case RANGED: return 600;
            case SIEGE:  return 300;
            default:     return 100;
        }
    }

    private static int goldFor(MinionType type) {
        switch (type) {
            case MELEE:  return 21;
            case RANGED: return 14;
            case SIEGE:  return 90;
            default:     return 21;
        }
    }

    public static Minion[] createWave(boolean includeSiege, int[] basePosition) {
        int count = includeSiege ? 7 : 6;
        Minion[] wave = new Minion[count];

        wave[0] = new Minion(MinionType.MELEE,  basePosition);
        wave[1] = new Minion(MinionType.MELEE,  basePosition);
        wave[2] = new Minion(MinionType.MELEE,  basePosition);
        wave[3] = new Minion(MinionType.RANGED, basePosition);
        wave[4] = new Minion(MinionType.RANGED, basePosition);
        wave[5] = new Minion(MinionType.RANGED, basePosition);

        if (includeSiege) {
            wave[3] = new Minion(MinionType.SIEGE, basePosition);
            wave[6] = new Minion(MinionType.RANGED,basePosition);
        }

        return wave;
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