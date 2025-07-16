package essential.test.UnitTestPractice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import essential.test.UnitTestPractice.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
}
