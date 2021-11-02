package tech.lucasbortolatto.dslearn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.lucasbortolatto.dslearn.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
