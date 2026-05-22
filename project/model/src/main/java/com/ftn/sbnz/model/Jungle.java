package com.ftn.sbnz.model;

import java.util.List;
import java.util.ArrayList;
 
public class Jungle {
    private int timer;
 
    private List<Camp> camps;
 
    public Jungle() {
        this.timer = 0;
        this.camps = new ArrayList<>();
        camps.add(new Camp(new int[]{20, 40}, 160, 300)); //Red
        camps.add(new Camp(new int[]{20, 60}, 90, 300)); //Blue
        camps.add(new Camp(new int[]{15, 50}, 50, 120)); //Wolves
        camps.add(new Camp(new int[]{25, 45}, 50, 120)); //Wraiths
        camps.add(new Camp(new int[]{15, 55}, 50, 120)); //Golems
        camps.add(new Camp(new int[]{25, 45}, 50, 120)); //Gromp
    }
 
    public int getTimer() { return timer; }
    public void setTimer(int timer) { this.timer = timer; }
 
    public List<Camp> getCamps() { return camps; }
    public void setCamps(List<Camp> camps) { this.camps = camps; }
}
 