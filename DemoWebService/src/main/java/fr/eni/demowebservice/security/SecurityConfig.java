package fr.eni.demowebservice.security;

import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private Filter jwtAuthenticationFilter;

    @Autowired
    private AuthenticationProvider jwtAuthenticationProvider;

    // Caduque avec JWT
//    @Bean
//    public UserDetailsManager userDetailsManager( DataSource dataSource ) {
//        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
//
//        // liste users actifs
//        userDetailsManager.setUsersByUsernameQuery("SELECT pseudo, password, 1 FROM Users WHERE pseudo = ?");
//
//        // récupère les authorisation de chauqe users
//        userDetailsManager.setAuthoritiesByUsernameQuery("SELECT pseudo, authority FROM Users WHERE pseudo = ?");
//
//        return userDetailsManager;
//    }

    @Bean
    SecurityFilterChain filterChain( HttpSecurity http, AuthenticationProvider authenticationProvider, HttpSession httpSession ) throws Exception {
        http.authorizeHttpRequests( auth -> {
                auth
                        // Tout le monde a accès à la racine
                        .requestMatchers("/").permitAll()
                        // Autorise l'accès en GET à bonhommes pour les ADMIN ET EMPLOYE
                        .requestMatchers(HttpMethod.GET, "/bonhommes/**").hasAnyRole("EMPLOYE", "ADMIN")

                        .requestMatchers(HttpMethod.POST, "/bonhommes/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/bonhommes/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/bonhommes/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/bonhommes/**").hasRole("ADMIN")

                        .anyRequest().denyAll();
        });

//        // utilise "l'authentification basic" de http
//        http.httpBasic(Customizer.withDefaults());

        http.authenticationProvider(authenticationProvider);
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        http.sessionManagement(session -> {
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });

        http.csrf(csrf -> csrf.disable());

        return http.build();
    }

}
