package vagapov.user_service.service;

import vagapov.user_service.model.UserDto;
import vagapov.user_service.models.CreateUserRequest;
import vagapov.user_service.models.ListUserRequest;
import vagapov.user_service.models.UpdateUserRequest;
import vagapov.user_service.models.UserRequest;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserDto getUser(UUID userGuid);

    UserDto createUser(UserRequest userRequest);

    UserDto updateUser(UUID userGuid, UserRequest user);

    UserDto deleteUser(UUID userGuid);

    List<UserDto> getListUser(ListUserRequest listUserRequest);
}
