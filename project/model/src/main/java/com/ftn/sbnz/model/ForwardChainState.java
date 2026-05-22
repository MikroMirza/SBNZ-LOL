package com.ftn.sbnz.model;

public class ForwardChainState {

    private boolean active;
    int stage; 
    public ForwardChainState() {
    }

    public ForwardChainState(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}
    
    
}