package com.server.onlineup.repository;

import com.server.onlineup.model.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<RoomEntity, String> {
    Optional<RoomEntity> findById(int id);

    void deleteById(int id);
}
