package com.server.onlineup.repository;

import com.server.onlineup.model.entity.OTPEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OTPRepository extends JpaRepository<OTPEntity, String> {
}