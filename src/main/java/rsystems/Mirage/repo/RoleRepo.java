package rsystems.Mirage.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import rsystems.Mirage.domain.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findRoleByName(String name);
}
