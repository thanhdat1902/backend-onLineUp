package com.server.onlineup.service.database;

import com.server.onlineup.model.entity.ProfileEntity;
import com.server.onlineup.repository.ProfileRepository;
import com.server.onlineup.security.principal.UserPrincipal;
import com.server.onlineup.service.implementation.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class ProfileService implements IUserService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Iterable<ProfileEntity> findAll() {
        return profileRepository.findAll();
    }

    @Override
    public Optional<ProfileEntity> findById(int id) {
        return profileRepository.findById(id);
    }

    @Override
    public ProfileEntity save(ProfileEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return profileRepository.save(user);
    }

    @Override
    public void remove(int id) {
        profileRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ProfileEntity> userOptional = profileRepository.findByEmail(username);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        return UserPrincipal.build(userOptional.get());
    }

    @Override
    public Optional<ProfileEntity> findByUsername(String username) {
        return profileRepository.findByEmail(username);
    }

    public boolean existingEmail(String email) {
        Optional<ProfileEntity> user = profileRepository.findByEmail(email);
        if (!user.isPresent()) {
            return false;
        }
        return true;
    }

    public void createAccount(String email, String fullName, String password) {
        save(new ProfileEntity(email, fullName, password));
    }

    public void updatePassword(String email, String password) {
        if (email != null) {
            Optional<ProfileEntity> user = profileRepository.findByEmail(email);
            user.get().setPassword(passwordEncoder.encode(password));
            profileRepository.save(user.get());
        }
    }

    public void createAccountFb(String email, String id) {
        save(new ProfileEntity(email, id));
    }

    public void updateAccount(String email, String fullname, String password) {
        if (email != null) {
            Optional<ProfileEntity> user = profileRepository.findByEmail(email);
            user.get().setPassword(passwordEncoder.encode(password));
            user.get().setFullname(fullname);
            profileRepository.save(user.get());
        }
    }

    public boolean isUseFb(String email, String idFb) {
        if (email == null) {
            return false;
        }
        Optional<ProfileEntity> user = profileRepository.findByEmail(email);
        if (user.get().getFbId().equals(idFb)) {
            return true;
        }
        return false;
    }
}
