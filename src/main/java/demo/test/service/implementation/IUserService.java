package demo.test.service.implementation;

import demo.test.model.entity.ProfileEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface IUserService extends IGeneralService<ProfileEntity>, UserDetailsService {
    Optional<ProfileEntity> findByUsername(String username);
}
