package tech.lucasbortolatto.dslearn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.lucasbortolatto.dslearn.entities.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
