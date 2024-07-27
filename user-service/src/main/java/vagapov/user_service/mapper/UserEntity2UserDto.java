package vagapov.user_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import vagapov.user_service.entity.UserEntity;
import vagapov.user_service.model.UserDto;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserEntity2UserDto {

    UserDto map(UserEntity entity);

    List<UserDto> map(List<UserEntity> entity);
}
