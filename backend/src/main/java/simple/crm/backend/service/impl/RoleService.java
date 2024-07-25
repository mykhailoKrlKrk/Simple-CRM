package simple.crm.backend.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import simple.crm.backend.model.Role;
import simple.crm.backend.model.RoleName;
import simple.crm.backend.repository.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getRoleByRoleName(RoleName roleName) {
        return roleRepository.findRoleByRoleName(roleName).orElseThrow(
                () -> new RuntimeException("Can't find role by role name:" + roleName));
    }
}
