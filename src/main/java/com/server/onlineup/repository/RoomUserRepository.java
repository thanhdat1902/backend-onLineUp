package com.server.onlineup.repository;

import com.server.onlineup.model.entity.RoomEntity;
import com.server.onlineup.model.entity.RoomUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomUserRepository extends JpaRepository<RoomUserEntity, String> {
    void deleteById(int id);

    List<RoomUserEntity> findByIdProfile(int id);

    List<RoomUserEntity> findByIdRoom(int id);
}
