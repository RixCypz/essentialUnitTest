package essential.test.UnitTestPractice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import essential.test.UnitTestPractice.dto.response.BaseResponse;
import essential.test.UnitTestPractice.dto.response.UserResponse;
import essential.test.UnitTestPractice.entity.User;
import essential.test.UnitTestPractice.mapper.UserMapper;
import essential.test.UnitTestPractice.service.UserService;

@RestController
@RequestMapping("/test")
public class UserController {
    private final UserMapper userMapper;
    private final UserService userService;

    public UserController(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello from /test/hello";
    }

    @GetMapping("/users")
    public ResponseEntity<BaseResponse<List<UserResponse>>> getAllUsers() {
        List<UserResponse> responses = userMapper.toDtoList(userService.getAllUsers());
        BaseResponse<List<UserResponse>> response = new BaseResponse<>(true, "Success", responses);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<BaseResponse<UserResponse>> getUserById(@PathVariable int id) {
        User user = userService.getUserById(id);
        UserResponse responseDto = userMapper.toDto(user);
        BaseResponse<UserResponse> response = new BaseResponse<>(true, "Success", responseDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/users")
    public ResponseEntity<BaseResponse<UserResponse>> createUser(@RequestBody User user) {
        User created = userService.createUser(user);
        UserResponse responseDto = userMapper.toDto(created);
        BaseResponse<UserResponse> response = new BaseResponse<>(true, "User created", responseDto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<BaseResponse<UserResponse>> updateUser(@PathVariable int id, @RequestBody User user) {
        user.setId(id);
        User updated = userService.updateUser(user);
        UserResponse responseDto = userMapper.toDto(updated);
        BaseResponse<UserResponse> response = new BaseResponse<>(true, "User updated", responseDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        BaseResponse<Void> response = new BaseResponse<>(true, "User deleted", null);
        return ResponseEntity.ok(response);
    }
}
