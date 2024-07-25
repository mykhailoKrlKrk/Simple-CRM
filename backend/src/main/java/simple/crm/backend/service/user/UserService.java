package simple.crm.backend.service.user;

import simple.crm.backend.dto.user.request.UserRegistrationRequestDto;
import simple.crm.backend.dto.user.response.UserResponseDto;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto request);
}
