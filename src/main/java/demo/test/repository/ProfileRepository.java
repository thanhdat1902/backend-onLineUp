package demo.test.repository;

import demo.test.model.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<ProfileEntity, String> {
    Optional<ProfileEntity> findByEmail(String username);

    void deleteById(int id);

    Optional<ProfileEntity> findById(int id);
}