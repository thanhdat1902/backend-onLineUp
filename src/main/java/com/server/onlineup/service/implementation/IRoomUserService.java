package com.server.onlineup.service.implementation;

import com.server.onlineup.model.entity.RoomUserEntity;

import java.util.List;

public interface IRoomUserService extends IGeneralService<RoomUserEntity> {
    List<RoomUserEntity> findByProfileId(String id);

    List<RoomUserEntity> findByRoomId(String id);
}
