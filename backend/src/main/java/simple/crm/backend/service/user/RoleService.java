package simple.crm.backend.service.user;

import simple.crm.backend.model.Role;
import simple.crm.backend.model.RoleName;

public interface RoleService {
    Role getRoleByRoleName(RoleName roleName);
}
