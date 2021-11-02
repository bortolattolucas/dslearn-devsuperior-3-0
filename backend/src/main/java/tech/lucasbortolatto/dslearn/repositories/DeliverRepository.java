package tech.lucasbortolatto.dslearn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.lucasbortolatto.dslearn.entities.Deliver;

public interface DeliverRepository extends JpaRepository<Deliver, Long> {
}
