package com.server.onlineup.repository;

import com.server.onlineup.model.entity.RoomAdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface RoomAdminRepository extends JpaRepository<RoomAdminEntity, String> {
    void deleteById(String id);

    List<RoomAdminEntity> findByIdProfile(String id);

    List<RoomAdminEntity> findByIdRoom(String id);
}
