package tech.lucasbortolatto.dslearn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.lucasbortolatto.dslearn.entities.Section;

public interface SectionRepository extends JpaRepository<Section, Long> {
}
