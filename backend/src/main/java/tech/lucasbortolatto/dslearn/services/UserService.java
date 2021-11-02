package tech.lucasbortolatto.dslearn.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.lucasbortolatto.dslearn.entities.User;
import tech.lucasbortolatto.dslearn.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

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
