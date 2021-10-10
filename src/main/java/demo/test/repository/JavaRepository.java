package demo.test.repository;

import demo.test.model.JavaObj;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JavaRepository extends CrudRepository<JavaObj, Long> {
}
