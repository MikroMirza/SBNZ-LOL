package com.ftn.sbnz.model;

import java.util.List;
import java.util.ArrayList;
 
public class Jungle {
    private int timer;
 
    private List<Camp> camps;
 
    public Jungle() {
        this.timer = 0;
        this.camps = new ArrayList<>();
        camps.add(new Camp(new int[]{20, 40}, 160, 300)); 
        camps.add(new Camp(new int[]{20, 60}, 90, 300)); 
        camps.add(new Camp(new int[]{15, 50}, 50, 120)); 
        camps.add(new Camp(new int[]{25, 45}, 50, 120));
    }
 
    public int getTimer() { return timer; }
    public void setTimer(int timer) { this.timer = timer; }
 
    public List<Camp> getCamps() { return camps; }
    public void setCamps(List<Camp> camps) { this.camps = camps; }
}
 