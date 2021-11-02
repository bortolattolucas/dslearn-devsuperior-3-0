package tech.lucasbortolatto.dslearn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.lucasbortolatto.dslearn.entities.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}
