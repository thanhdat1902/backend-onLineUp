package demo.test.service.database;

import demo.test.model.entity.ProfileEntity;
import demo.test.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    @Autowired
    ProfileRepository profileRepository;

    public void createAccount(String username, String password, String email) {
        ProfileEntity user = new ProfileEntity(username, password, email);
        profileRepository.save(user);
    }

    public boolean existingEmail(String email) {
        List<ProfileEntity> user = profileRepository.findByEmail(email);
        if (user.isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean existingUsername(String username) {
        List<ProfileEntity> user = profileRepository.findByUsername(username);
        if (user.isEmpty()) {
            return false;
        }
        return true;
    }
}
