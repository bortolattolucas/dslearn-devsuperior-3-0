package tech.lucasbortolatto.dslearn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.lucasbortolatto.dslearn.entities.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
