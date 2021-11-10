package com.server.onlineup.model.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class RoomProfileKey implements Serializable {
    @Column(name = "profile_id", nullable = false)
    String profile;

    @Column(name = "room_id", nullable = false)
    String room;

    public RoomProfileKey(String roomId, String profileId) {
        this.profile = profileId;
        this.room = roomId;
    }
}
