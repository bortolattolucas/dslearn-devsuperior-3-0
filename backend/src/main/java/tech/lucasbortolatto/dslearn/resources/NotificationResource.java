package tech.lucasbortolatto.dslearn.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.lucasbortolatto.dslearn.dto.NotificationDTO;
import tech.lucasbortolatto.dslearn.services.NotificationService;

@RestController
@RequestMapping(value = "/notifications")
public class NotificationResource {

    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public ResponseEntity<Page<NotificationDTO>> findById(@RequestParam(name = "unreadOnly", defaultValue = "false") Boolean unreadOnly,
                                                          Pageable pageable) {
        return ResponseEntity.ok(notificationService.getNotificationsForCurrentUser(unreadOnly, pageable));
    }
}
