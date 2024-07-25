package simple.crm.backend.mapper;

import org.mapstruct.Mapper;
import simple.crm.backend.config.MapperConfig;
import simple.crm.backend.dto.user.request.UserRegistrationRequestDto;
import simple.crm.backend.dto.user.response.UserResponseDto;
import simple.crm.backend.model.User;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserResponseDto toDto(User user);

    User toModel(UserRegistrationRequestDto requestDto);
}
