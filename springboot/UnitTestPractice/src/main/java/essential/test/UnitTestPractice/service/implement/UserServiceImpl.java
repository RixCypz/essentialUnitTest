package essential.test.UnitTestPractice.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import essential.test.UnitTestPractice.dto.request.UserRequest;
import essential.test.UnitTestPractice.entity.Order;
import essential.test.UnitTestPractice.entity.User;
import essential.test.UnitTestPractice.repository.UserRepository;
import essential.test.UnitTestPractice.service.UserService;
import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    @Override
    @Transactional
    public User createUser(UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());

        if (userRequest.getOrders() != null) {
            List<Order> orders = userRequest.getOrders().stream().map(orderReq -> {
                Order order = new Order();
                order.setProductName(orderReq.getProductName());
                order.setQuantity(orderReq.getQuantity());
                order.setUser(user);
                return order;
            }).collect(Collectors.toList());
            user.setOrders(orders);
        }

        User savedUser = userRepository.save(user);
        return savedUser;
    }

    @Override
    public User updateUser(User user) {
        if (!userRepository.existsById(user.getId())) {
            throw new RuntimeException("User not found with id " + user.getId());
        }
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(int id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id " + id);
        }
        userRepository.deleteById(id);
    }
}

