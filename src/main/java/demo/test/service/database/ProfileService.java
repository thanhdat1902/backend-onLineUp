package demo.test.service.database;

import demo.test.model.entity.ProfileEntity;
import demo.test.model.helper.UserPrincipal;
import demo.test.repository.ProfileRepository;
import demo.test.service.helper.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

<<<<<<< HEAD:src/main/java/demo/test/service/ProfileService.java
import java.util.Optional;
=======
import java.util.List;

@Service
public class ProfileService {
>>>>>>> 4fe56e13109a40c949d6899270c426155da642fd:src/main/java/demo/test/service/database/ProfileService.java

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
        List<ProfileEntity> user = profileRepository.findByEmail(email);
        if (user.isEmpty()) {
            return false;
        }
        return true;
    }
}
