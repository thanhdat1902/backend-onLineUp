package com.server.onlineup.service.database;

import com.server.onlineup.model.entity.RoomUserEntity;
import com.server.onlineup.repository.RoomUserRepository;
import com.server.onlineup.service.implementation.IRoomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomUserService implements IRoomUserService {
    @Autowired
    private RoomUserRepository roomUserRepository;

    @Override
    public Iterable<RoomUserEntity> findAll() {
        return roomUserRepository.findAll();
    }

    @Override
    public RoomUserEntity save(RoomUserEntity roomUserEntity) {
        return roomUserRepository.save(roomUserEntity);
    }

    @Override
    public void remove(String id) {
        roomUserRepository.deleteById(id);
    }

    @Override
    public List<RoomUserEntity> findByProfileId(String id) {
        return roomUserRepository.findByIdProfile(id);
    }

    @Override
    public List<RoomUserEntity> findByRoomId(String id) {
        return roomUserRepository.findByIdRoom(id);
    }
}
