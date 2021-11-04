package tech.lucasbortolatto.dslearn.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tech.lucasbortolatto.dslearn.entities.Notification;
import tech.lucasbortolatto.dslearn.entities.User;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query("SELECT obj FROM Notification obj WHERE "
            + "(obj.user = :user) AND "
            + "(:unreadOnly = false OR obj.read = false) " // macete pra trazer todas qdo variavel for falsa ou nao informada
            + "ORDER BY obj.moment DESC")
    Page<Notification> find(User user, boolean unreadOnly, Pageable pageable);
}
