package vagapov.user_service.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import vagapov.user_service.api.UserServiceApi;
import vagapov.user_service.models.*;
import vagapov.user_service.service.dispatcher.UserDispatcherService;

import java.util.UUID;

@Controller
@Slf4j
public class UserController implements UserServiceApi {

    private final UserDispatcherService userService;

    public UserController(UserDispatcherService userService) {;
        this.userService = userService;
    }

    @Override
    public ResponseEntity<ExtendedUserResponse> updateUser(UpdateUserRequest updateUserRequest) {
        log.info("Принят запрос на изменение пользователя: {}", updateUserRequest);
        ExtendedUserResponse extendedUserResponse = userService.updateUser(updateUserRequest);
        log.info("Ответ на запрос изменения пользователя: {}", extendedUserResponse);
        return ResponseEntity.ok(extendedUserResponse);
    }

    @Override
    public ResponseEntity<ExtendedUserResponse> createUser(CreateUserRequest createUserRequest) {
        log.info("Принят запрос на создание пользователя: {}", createUserRequest);
        ExtendedUserResponse extendedUserResponse = userService.createUser(createUserRequest);
        log.info("Ответ на запрос создания пользователя: {}", extendedUserResponse);
        return ResponseEntity.ok(extendedUserResponse);
    }

    @Override
    public ResponseEntity<ExtendedUserResponse> deleteUser(Integer version, UUID userGuid) {
        log.info("Принят запрос на удаления пользователя с version = {} и userGuid = {}", version, userGuid);
        ExtendedUserResponse extendedUserResponse = userService.deleteUser(version, userGuid);
        log.info("Ответ на запрос удаления пользователя c userGuid = {} : {}", userGuid, extendedUserResponse);
        return ResponseEntity.ok(extendedUserResponse);
    }

    @Override
    public ResponseEntity<ExtendedUserResponse> getUser(UUID userGuid) {
        log.info("Принят запрос на информацию о пользователе с userGuid = {}", userGuid);
        ExtendedUserResponse extendedUserResponse = userService.getUser(userGuid);
        log.info("Ответ на запрос информации по пользователю с userGuid = {} : {}", userGuid, extendedUserResponse);
        return ResponseEntity.ok(extendedUserResponse);
    }

    @Override
    public ResponseEntity<ListUserResponse> listUser(ListUserRequest listUserRequest) {
        log.info("Принят запрос на информации по списку пользователей: {}", listUserRequest);
        ListUserResponse listUser = userService.getListUser(listUserRequest);
        log.info("Ответ на запрос информации по списку пользователей: {}", listUser);
        return ResponseEntity.ok(listUser);
    }
}
