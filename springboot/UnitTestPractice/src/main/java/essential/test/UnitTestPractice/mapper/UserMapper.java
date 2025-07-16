package essential.test.UnitTestPractice.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import essential.test.UnitTestPractice.dto.response.UserResponse;
import essential.test.UnitTestPractice.entity.User;

@Mapper(componentModel="spring")
public interface UserMapper {
    UserResponse toDto(User user);
    List<UserResponse> toDtoList(List<User> users);
}
