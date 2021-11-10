package com.server.onlineup.repository;

import com.server.onlineup.model.entity.RoomAdminEntity;
import com.server.onlineup.model.entity.RoomUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface RoomAdminRepository extends JpaRepository<RoomAdminEntity, String> {
    void deleteById(int id);

    List<RoomAdminEntity> findByIdProfile(int id);

    List<RoomAdminEntity> findByIdRoom(int id);
}
