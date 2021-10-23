package demo.test.repository;

import demo.test.model.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfileRepository extends JpaRepository<ProfileEntity, String> {
    @Query(
            value = "SELECT * FROM Profile p WHERE p.email = ?1",
            nativeQuery = true
    )
    List<ProfileEntity> findByEmail(String email);

    @Query(
            value = "SELECT * FROM Profile p WHERE p.username = ?1",
            nativeQuery = true
    )
    List<ProfileEntity> findByUsername(String username);
}