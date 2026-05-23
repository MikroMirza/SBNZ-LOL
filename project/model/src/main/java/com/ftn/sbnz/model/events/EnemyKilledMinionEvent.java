package com.ftn.sbnz.model.events;

import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;
import org.kie.api.definition.type.Expires;

@Role(Role.Type.EVENT)
@Timestamp("timestamp")
@Expires("45s")
public class EnemyKilledMinionEvent {
    private long timestamp;

    public EnemyKilledMinionEvent(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getTimestamp() { return timestamp; }
}