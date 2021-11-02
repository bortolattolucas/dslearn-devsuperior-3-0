package tech.lucasbortolatto.dslearn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.lucasbortolatto.dslearn.entities.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
