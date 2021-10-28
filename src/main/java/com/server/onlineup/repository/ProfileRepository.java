package com.server.onlineup.repository;

import com.server.onlineup.model.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<ProfileEntity, String> {

    Optional<ProfileEntity> findByEmail(String email);

    void deleteById(int id);

    Optional<ProfileEntity> findById(int id);
}