package com.server.onlineup.service.database;

import com.server.onlineup.model.entity.RoomAdminEntity;
import com.server.onlineup.repository.RoomAdminRepository;
import com.server.onlineup.service.implementation.IRoomAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomAdminService implements IRoomAdminService {
    @Autowired
    private RoomAdminRepository roomAdminRepository;

    @Override
    public Iterable<RoomAdminEntity> findAll() {
        return roomAdminRepository.findAll();
    }

    @Override
    public RoomAdminEntity save(RoomAdminEntity roomAdminEntity) {
        return roomAdminRepository.save(roomAdminEntity);
    }

    @Override
    public void remove(String id) {
        roomAdminRepository.deleteById(id);
    }

    @Override
    public List<RoomAdminEntity> findByProfileId(String id) {
        return roomAdminRepository.findByIdProfile(id);
    }

    @Override
    public List<RoomAdminEntity> findByRoomId(String id) {
        return roomAdminRepository.findByIdRoom(id);
    }
}
