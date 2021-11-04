package tech.lucasbortolatto.dslearn.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.lucasbortolatto.dslearn.dto.NotificationDTO;
import tech.lucasbortolatto.dslearn.entities.Notification;
import tech.lucasbortolatto.dslearn.entities.User;
import tech.lucasbortolatto.dslearn.repositories.NotificationRepository;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private AuthService authService;

    @Transactional(readOnly = true)
    public Page<NotificationDTO> getNotificationsForCurrentUser(boolean unreadOnly, Pageable pageable) {
        User user = authService.getAuthenticatedUser();
        Page<Notification> page = notificationRepository.find(user, unreadOnly, pageable);
        return page.map(NotificationDTO::new);
    }
}
