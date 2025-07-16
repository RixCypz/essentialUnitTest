package essential.test.UnitTestPractice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import essential.test.UnitTestPractice.dto.response.BaseResponse;
import essential.test.UnitTestPractice.dto.response.UserResponse;
import essential.test.UnitTestPractice.mapper.UserMapper;
import essential.test.UnitTestPractice.service.UserService;

@RestController
@RequestMapping("/test")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    private final UserService userService;

    public UserController(UserService userService){
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
}
