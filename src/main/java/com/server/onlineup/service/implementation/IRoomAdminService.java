package com.server.onlineup.service.implementation;

import com.server.onlineup.model.entity.RoomAdminEntity;

import java.util.List;

public interface IRoomAdminService extends IGeneralService<RoomAdminEntity>{
    List<RoomAdminEntity> findByProfileId(int id);

    List<RoomAdminEntity> findByRoomId(int id);
}
