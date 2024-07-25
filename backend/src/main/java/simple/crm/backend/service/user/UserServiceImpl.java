package simple.crm.backend.service.user;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import simple.crm.backend.dto.user.request.UserRegistrationRequestDto;
import simple.crm.backend.dto.user.response.UserResponseDto;
import simple.crm.backend.mapper.UserMapper;
import simple.crm.backend.model.RoleName;
import simple.crm.backend.model.User;
import simple.crm.backend.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final RoleService roleService;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto request) {
        User user = userMapper.toModel(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(Set.of(roleService.getRoleByRoleName(RoleName.ROLE_USER)));
        User savedUser = userRepository.save(user);

        return userMapper.toDto(savedUser);
    }
}
