package com.server.onlineup.service.implementation;


import com.server.onlineup.model.entity.RoomEntity;

import java.util.Optional;

public interface IRoomService extends IGeneralService<RoomEntity> {
    Optional<RoomEntity> findById(String id);

}
