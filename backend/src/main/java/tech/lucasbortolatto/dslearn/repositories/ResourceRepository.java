package tech.lucasbortolatto.dslearn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.lucasbortolatto.dslearn.entities.Resource;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
}
