package tech.lucasbortolatto.dslearn.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.lucasbortolatto.dslearn.dto.UserDTO;
import tech.lucasbortolatto.dslearn.entities.User;
import tech.lucasbortolatto.dslearn.repositories.UserRepository;
import tech.lucasbortolatto.dslearn.services.exceptions.ResourceNotFoundException;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthService authService;

    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        authService.validateSelfOrAdmin(id);
        Optional<User> obj = userRepository.findById(id);
        User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new UserDTO(entity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);

        if (user == null) {
            logger.error("User not found: " + username);
            throw new UsernameNotFoundException("Email not found");
        }

        // exibe no console que o usuario foi encontrado pra auxiliar o processo dos estudos
        logger.info("User found: " + username);
        // como a nossa classe User implementa UserDetails, retorna-se o usuario encontrado
        return user;
    }
}
