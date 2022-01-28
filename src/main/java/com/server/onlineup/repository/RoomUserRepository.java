package com.server.onlineup.repository;

import com.server.onlineup.model.entity.RoomUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomUserRepository extends JpaRepository<RoomUserEntity, String> {
    void deleteById(String id);

    List<RoomUserEntity> findByIdProfile(String id);

    List<RoomUserEntity> findByIdRoom(String id);
}
