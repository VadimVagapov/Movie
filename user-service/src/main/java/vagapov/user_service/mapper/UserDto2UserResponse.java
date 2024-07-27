package vagapov.user_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import vagapov.user_service.model.UserDto;
import vagapov.user_service.models.ListUserDataResponse;
import vagapov.user_service.models.UserResponse;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserDto2UserResponse {

    UserResponse map(UserDto user);

    List<ListUserDataResponse> map(List<UserDto> user);
}
