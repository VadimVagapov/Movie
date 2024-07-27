package vagapov.user_service.service.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vagapov.user_service.entity.UserEntity;
import vagapov.user_service.mapper.UserEntity2UserDto;
import vagapov.user_service.mapper.UserRequest2UserEntity;
import vagapov.user_service.model.UserDto;
import vagapov.user_service.models.ListUserRequest;
import vagapov.user_service.models.UserRequest;
import vagapov.user_service.repository.UserRepository;
import vagapov.user_service.service.UserService;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Primary
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserEntity2UserDto entity2UserDtoMapper;
    private final UserRequest2UserEntity request2UserMapper;

    public UserServiceImpl(UserRepository userRepository,
                           UserEntity2UserDto entity2UserDtoMapper,
                           UserRequest2UserEntity request2UserMapper) {
        this.userRepository = userRepository;
        this.entity2UserDtoMapper = entity2UserDtoMapper;
        this.request2UserMapper = request2UserMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getUser(UUID userGuid) {
        return entity2UserDtoMapper.map(userRepository.getByUserGuid(userGuid));
    }

    @Override
    @Transactional
    public UserDto createUser(UserRequest userRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserGuid(UUID.randomUUID());
        userEntity.setCreateDate(OffsetDateTime.now());
        userEntity.setDeleted(false);
        request2UserMapper.map(userEntity, userRequest);
        userEntity = userRepository.saveAndFlush(userEntity);
        return entity2UserDtoMapper.map(userEntity);
    }

    @Override
    @Transactional
    public UserDto updateUser(UUID userGuid, UserRequest user) {
        UserEntity userEntity = userRepository.getByUserGuid(userGuid);
        request2UserMapper.map(userEntity, user);
        userEntity.setChangeDate(OffsetDateTime.now());
        userEntity = userRepository.saveAndFlush(userEntity);
        return entity2UserDtoMapper.map(userEntity);
    }

    @Override
    @Transactional
    public UserDto deleteUser(UUID userGuid) {
        UserEntity userEntity = userRepository.getByUserGuid(userGuid);
        userRepository.delete(userEntity);
        return entity2UserDtoMapper.map(userEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getListUser(ListUserRequest listUserRequest) {
        List<UserEntity> userEntityList = userRepository.findAll();
        return entity2UserDtoMapper.map(userEntityList);
    }
}
