package tech.lucasbortolatto.dslearn.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.lucasbortolatto.dslearn.dto.DeliverRevisionDTO;
import tech.lucasbortolatto.dslearn.entities.Deliver;
import tech.lucasbortolatto.dslearn.repositories.DeliverRepository;

@Service
public class DeliverService {

    @Autowired
    private DeliverRepository deliverRepository;

    @Transactional
    public void saveRevision(Long deliveryId, DeliverRevisionDTO dto) {
        Deliver deliver = deliverRepository.getOne(deliveryId);
        deliver.setStatus(dto.getStatus());
        deliver.setFeedback(dto.getFeedback());
        deliver.setCorrectCount(dto.getCorrectCount());
        deliverRepository.save(deliver);
    }
}
