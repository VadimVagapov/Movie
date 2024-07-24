package vagapov.user_service.controller;

import org.springframework.http.ResponseEntity;
import vagapov.user_service.api.UserServiceApi;
import vagapov.user_service.models.ExtendedUserResponse;
import vagapov.user_service.models.UpdateUserRequest;

public class UserController implements UserServiceApi {

    @Override
    public ResponseEntity<ExtendedUserResponse> updateUser(UpdateUserRequest updateUserRequest) {
        return null;
    }
}
