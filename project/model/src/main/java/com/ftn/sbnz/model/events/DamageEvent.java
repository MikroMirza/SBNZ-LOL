package com.ftn.sbnz.model.events;

import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;
import org.kie.api.definition.type.Expires;

@Role(Role.Type.EVENT)
@Timestamp("timestamp")
@Expires("60s")
public class DamageEvent {
    private long timestamp;
    private String championId;
    private int damageAmount;

    public DamageEvent(long timestamp, String championId, int damageAmount) {
        this.timestamp = timestamp;
        this.championId = championId;
        this.damageAmount = damageAmount;
    }

    public long getTimestamp() { return timestamp; }
    public String getChampionId() { return championId; }
    public int getDamageAmount() { return damageAmount; }
}
