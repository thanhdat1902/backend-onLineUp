package com.server.onlineup.service.implementation;

import com.server.onlineup.model.entity.RoomAdminEntity;
import com.server.onlineup.model.entity.RoomUserEntity;

import java.util.List;

public interface IRoomUserService extends IGeneralService<RoomUserEntity>{
    List<RoomUserEntity> findByProfileId(int id);

    List<RoomUserEntity> findByRoomId(int id);
}
