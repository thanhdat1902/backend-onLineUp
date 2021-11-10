package com.server.onlineup.model.entity;

import com.server.onlineup.common.utils.TimeUtils;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "RoomAdmin")
public class RoomAdminEntity {

    @EmbeddedId
    RoomProfileKey id;

    @ManyToOne
    @MapsId("profile")
    @JoinColumn(name = "profile_id",insertable=false,updatable=false)
    ProfileEntity profileEntity;

    @ManyToOne
    @MapsId("room")
    @JoinColumn(name = "room_id", insertable=false,updatable=false)
    RoomEntity roomEntity;

    @Enumerated(EnumType.STRING)
    private Role role = Role.HOST;

    public void changeRole() {
        if(this.role.equals(Role.HOST)){
            this.role = Role.CO_HOST;
        }else {
            this.role = Role.HOST;
        }
    }

    private enum Role {
        HOST, CO_HOST
    }

    public RoomAdminEntity() {}
    public RoomAdminEntity(ProfileEntity profileEntity, RoomEntity room) {
        this.roomEntity = room;
        this.profileEntity = profileEntity;
        this.id = new RoomProfileKey(roomEntity.getId(), profileEntity.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        RoomAdminEntity that = (RoomAdminEntity) o;
        return Objects.equals(profileEntity, that.profileEntity) &&
                Objects.equals(roomEntity, that.roomEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profileEntity, roomEntity);
    }
    private long joinTime= TimeUtils.getCurrentTimestamp();

}

