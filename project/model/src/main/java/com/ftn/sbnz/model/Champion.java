package com.ftn.sbnz.model;


public class Champion extends Character {
 
    public enum PlayerType { AGGRESSIVE, BALANCED, PASSIVE }
    public enum ChampionType { MAGE, ASSASSIN, FIGHTER }
 
    private float powerMult;
    private int cooldownInt;
    private float cooldownFloat;
    private PlayerType playerType;
    private int chanceOnHit;
    private boolean ranged;
 
    private ChampionType championType;
    private double gold;
    private int ad;
    private int kills;
    private int deaths;
    private int csScore;
    private boolean Dead;
    private int respawnTimer;
    private int ultCooldown;
    private Position currentPosition;
    private int lastSeenTimer;
    private int lastSeenHp;
 
    public Champion() {}
 
    public Champion(PlayerType playerType, ChampionType championType, boolean ranged) {
        super(1000,0,new int[]{50, 50},350,0,500,1,500,true);
        this.powerMult = 1.0f;
        this.cooldownInt = 0;
        this.cooldownFloat = 0f;
        this.playerType = playerType;
        this.chanceOnHit = 0;
        this.ranged = ranged;
        this.championType = championType;
        this.gold = 500;
        this.ad = 60;
        this.kills = 0;
        this.deaths = 0;
        this.csScore = 0;
        this.Dead = false;
        this.respawnTimer = 0;
        this.ultCooldown = 0;
        this.currentPosition = Position.MIDLANE;
        this.lastSeenTimer = 0;
        this.lastSeenHp = 100;
    }
 
    public int getHpPercent() { return getHp() / 10; }
    public void setHpPercent(int percent) { setHp(percent * 10); }

    public int getManaPercent() { return (int)((getMana() / 500.0) * 100); }
    public void setManaPercent(int percent) { setMana((int)(percent / 100.0 * 500)); }
 
 
    public float getPowerMult() { return powerMult; }
    public void setPowerMult(float powerMult) { this.powerMult = powerMult; }
 
    public int getCooldownInt() { return cooldownInt; }
    public void setCooldownInt(int cooldownInt) { this.cooldownInt = cooldownInt; }
 
    public float getCooldownFloat() { return cooldownFloat; }
    public void setCooldownFloat(float cooldownFloat) { this.cooldownFloat = cooldownFloat; }
 
    public PlayerType getPlayerType() { return playerType; }
    public void setPlayerType(PlayerType playerType) { this.playerType = playerType; }
 
    public int getChanceOnHit() { return chanceOnHit; }
    public void setChanceOnHit(int chanceOnHit) { this.chanceOnHit = chanceOnHit; }
 
    public boolean isRanged() { return ranged; }
    public void setRanged(boolean ranged) { this.ranged = ranged; }
 
    public ChampionType getChampionType() { return championType; }
    public void setChampionType(ChampionType championType) { this.championType = championType; }
 
    public double getGold() { return gold; }
    public void setGold(double gold) { this.gold = gold; }
 
    public int getAd() { return ad; }
    public void setAd(int ad) { this.ad = ad; }
 
    public int getKills() { return kills; }
    public void setKills(int kills) { this.kills = kills; }
 
    public int getDeaths() { return deaths; }
    public void setDeaths(int deaths) { this.deaths = deaths; }
 
    public int getCsScore() { return csScore; }
    public void setCsScore(int csScore) { this.csScore = csScore; }
 
    public boolean isDead() { return Dead; }
    public void setDead(boolean dead) { Dead = dead; }
 
    public int getRespawnTimer() { return respawnTimer; }
    public void setRespawnTimer(int respawnTimer) { this.respawnTimer = respawnTimer; }
 
    public int getUltCooldown() { return ultCooldown; }
    public void setUltCooldown(int ultCooldown) { this.ultCooldown = ultCooldown; }
 
    public Position getCurrentPosition() { return currentPosition; }
    public void setCurrentPosition(Position pos) { this.currentPosition = pos; }
 
    public int getLastSeenTimer() { return lastSeenTimer; }
    public void setLastSeenTimer(int lastSeenTimer) { this.lastSeenTimer = lastSeenTimer; }
 
    public int getLastSeenHp() { return lastSeenHp; }
    public void setLastSeenHp(int lastSeenHp) { this.lastSeenHp = lastSeenHp; }
 
    @Override
    public String toString() {
        return String.format("[HP:%d%% Mana:%d%% Lvl:%d Gold:%.0f CS:%d Pos:%s Type:%s]",
                getHpPercent(), getManaPercent(), getLevel(),
                gold, csScore, currentPosition, playerType);
    }
}