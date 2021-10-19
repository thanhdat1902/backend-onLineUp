package demo.test.repository;

import demo.test.model.entity.OTPEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OTPRepository extends JpaRepository<OTPEntity, String> {
}