package demo.test.service.database;

import demo.test.model.entity.ProfileEntity;
import demo.test.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    ProfileRepository profileRepository;
    //service bay h gọi database để tạo ra 1 cái account.

    public void createAccount(String username, String password, String email) {
        ProfileEntity user = new ProfileEntity(username, password, email);
        profileRepository.save(user);
    }

    public boolean existingEmail(String email) {
        ProfileEntity user = profileRepository.getById(email);
        if (user == null) {
            return false;
        }
        return true;
    }
}
