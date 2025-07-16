package essential.test.UnitTestPractice.service;

import java.util.List;
import essential.test.UnitTestPractice.entity.User;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(int id);
    User createUser(User user);
    User updateUser(User user);
    void deleteUser(int id);
}
