package com.ftn.sbnz.model.events;

import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;
import org.kie.api.definition.type.Expires;

@Role(Role.Type.EVENT)
@Timestamp("timestamp")
@Expires("60s")
public class LowHpEvent {
    private long timestamp;
    private String championId;

    public LowHpEvent(long timestamp, String championId) {
        this.timestamp = timestamp;
        this.championId = championId;
    }

    public long getTimestamp() { return timestamp; }
    public String getChampionId() { return championId; }
}