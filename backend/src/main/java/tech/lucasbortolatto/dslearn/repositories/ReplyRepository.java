package tech.lucasbortolatto.dslearn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.lucasbortolatto.dslearn.entities.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
