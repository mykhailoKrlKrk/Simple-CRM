package simple.crm.backend.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import simple.crm.backend.model.Role;
import simple.crm.backend.model.RoleName;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRoleByRoleName(RoleName roleName);
}
