package tech.lucasbortolatto.dslearn.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.lucasbortolatto.dslearn.entities.User;
import tech.lucasbortolatto.dslearn.repositories.UserRepository;
import tech.lucasbortolatto.dslearn.services.exceptions.ForbiddenException;
import tech.lucasbortolatto.dslearn.services.exceptions.UnauthorizedException;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public User getAuthenticatedUser() {
        try {
            // Pega o username do usuário autenticado na request atual, pego automaticamente pelo
            // contexto do security na autenticação
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            return userRepository.findByEmail(username);
        } catch (Exception e) {
            // se por algum motivo não for possível identificar o cabra, vai disparar a exception abaixo
            throw new UnauthorizedException("Invalid user");
        }
    }

    public void validateSelfOrAdmin(Long userId) {
        User user = getAuthenticatedUser();

        // se nao for o proprio usuario e se não for um admin ele nao vai poder consultar outro usuario
        // como o User tem um fetch eager em suas roles, já é possível identificar isso
        if (!user.getId().equals(userId) && !user.hasHole("ROLE_ADMIN")) {
            throw new ForbiddenException("Access denied");
        }
    }
}
