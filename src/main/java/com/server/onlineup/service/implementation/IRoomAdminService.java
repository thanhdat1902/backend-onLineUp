package com.server.onlineup.service.implementation;

import com.server.onlineup.model.entity.RoomAdminEntity;

import java.util.List;

public interface IRoomAdminService extends IGeneralService<RoomAdminEntity> {
    List<RoomAdminEntity> findByProfileId(String id);

    List<RoomAdminEntity> findByRoomId(String id);
}
