package com.server.onlineup.service.implementation;

import com.server.onlineup.model.entity.ProfileEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface IUserService extends IGeneralService<ProfileEntity>, UserDetailsService {
    Optional<ProfileEntity> findByUsername(String username);

    Optional<ProfileEntity> findById(String id);
}
