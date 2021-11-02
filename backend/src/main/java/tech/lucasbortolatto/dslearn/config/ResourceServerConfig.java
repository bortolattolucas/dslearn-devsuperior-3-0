package tech.lucasbortolatto.dslearn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Arrays;

// configuração do ResourceServer do checklist do oauth
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    // Objeto que representa o ambiente de execução da aplicação
    // Instanciado para liberar acesso ao H2 console
    @Autowired
    private Environment environment;

    // rotas abertas para todos
    private static final String[] PUBLIC = {"/oauth/token", "/h2-console/**"};

    @Autowired
    private JwtTokenStore jwtTokenStore;

    // com esse método, o resourceServer vai ser capaz de analisar o JWT inteiramente
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(jwtTokenStore);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // libera o acesso ao h2 console
        if (Arrays.asList(environment.getActiveProfiles()).contains("test")) {
            http.headers().frameOptions().disable();
        }

        http.authorizeRequests() // configura as autorizações por rotas/roles
                .antMatchers(PUBLIC).permitAll()
                .anyRequest().authenticated();
    }
}
