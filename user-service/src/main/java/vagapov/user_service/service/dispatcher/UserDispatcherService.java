package vagapov.user_service.service.dispatcher;

import vagapov.user_service.models.*;

import java.util.UUID;

public interface UserDispatcherService {

    ExtendedUserResponse getUser(UUID userGuid);

    ExtendedUserResponse createUser(CreateUserRequest createUserRequest);

    ExtendedUserResponse updateUser(UpdateUserRequest updateUserRequest);

    ExtendedUserResponse deleteUser(Integer version, UUID userGuid);

    ListUserResponse getListUser(ListUserRequest listUserRequest);
}
