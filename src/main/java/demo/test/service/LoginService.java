package demo.test.service;

import demo.test.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    ProfileRepository profileRepository;

    public boolean handleLogin(String username, String password) {
        return true;
    }
}
