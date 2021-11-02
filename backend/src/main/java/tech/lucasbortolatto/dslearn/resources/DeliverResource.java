package tech.lucasbortolatto.dslearn.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.lucasbortolatto.dslearn.dto.DeliverRevisionDTO;
import tech.lucasbortolatto.dslearn.services.DeliverService;

@RestController
@RequestMapping(value = "/deliveries")
public class DeliverResource {

    @Autowired
    private DeliverService deliverService;

    // esse preauthorize poderia ser na camada de serviço, mas é bom deixar aqui pra nem precisar processar a validação
    // vai de acordo com o gosto do dev
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> saveRevision(@PathVariable Long id, @RequestBody DeliverRevisionDTO dto) {
        deliverService.saveRevision(id, dto);
        return ResponseEntity.noContent().build();
    }
}
