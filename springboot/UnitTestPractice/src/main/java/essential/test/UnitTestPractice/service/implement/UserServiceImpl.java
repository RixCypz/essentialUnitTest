package essential.test.UnitTestPractice.service.implement;

import java.util.List;

import org.springframework.stereotype.Service;

import essential.test.UnitTestPractice.entity.User;
import essential.test.UnitTestPractice.repository.UserRepository;
import essential.test.UnitTestPractice.service.UserService;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers(){
        List<User> users = userRepository.getAllUsers();
        return users;
    }
}
