package tech.lucasbortolatto.dslearn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.lucasbortolatto.dslearn.entities.Offer;

public interface OfferRepository extends JpaRepository<Offer, Long> {
}
