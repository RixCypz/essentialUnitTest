package essential.test.UnitTestPractice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import essential.test.UnitTestPractice.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
    @Query(nativeQuery=true, 
    value = "select * from playground.user")
    List<User> getAllUsers();
}
