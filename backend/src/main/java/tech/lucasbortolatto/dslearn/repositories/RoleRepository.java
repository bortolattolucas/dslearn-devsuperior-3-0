package tech.lucasbortolatto.dslearn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.lucasbortolatto.dslearn.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
