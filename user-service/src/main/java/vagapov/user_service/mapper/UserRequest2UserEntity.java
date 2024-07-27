package vagapov.user_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import vagapov.user_service.entity.UserEntity;
import vagapov.user_service.models.UserRequest;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserRequest2UserEntity {
    void map(@MappingTarget UserEntity target, UserRequest source);
}
