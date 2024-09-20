package fr.eni.cave_a_vin.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager( DataSource dataSource ) {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);

        // liste users actifs
        userDetailsManager.setUsersByUsernameQuery("SELECT login, password, 1 FROM cav_user WHERE login = ?");

        // récupère les authorisation de chauqe users
        userDetailsManager.setAuthoritiesByUsernameQuery("SELECT login, authority FROM cav_user WHERE login = ?");

        return userDetailsManager;
    }

    @Bean
    SecurityFilterChain filterChain( HttpSecurity http) throws Exception {
        http.authorizeHttpRequests( auth -> {
                auth
                        // Tout le monde a accès à la racine
                        .requestMatchers("/").permitAll()

                        .requestMatchers(HttpMethod.GET, "/caveavin/bouteilles").permitAll()
                        .requestMatchers(HttpMethod.POST, "/caveavin/bouteilles").hasRole("OWNER")
                        .requestMatchers(HttpMethod.PUT, "/caveavin/bouteilles/**").hasRole("OWNER")
                        .requestMatchers(HttpMethod.PATCH, "/caveavin/bouteilles/**").hasRole("OWNER")
                        .requestMatchers(HttpMethod.DELETE, "/caveavin/bouteilles/*").hasRole("OWNER")

                        .requestMatchers(HttpMethod.GET, "/caveavin/paniers").hasAnyRole("OWNER", "CLIENT")
                        .requestMatchers(HttpMethod.GET, "/caveavin/paniers/**").hasAnyRole("OWNER", "CLIENT")
                        .requestMatchers(HttpMethod.POST, "/caveavin/paniers").hasRole("OWNER")
                        .requestMatchers(HttpMethod.PUT, "/caveavin/paniers/*").hasRole("OWNER")
                        .requestMatchers(HttpMethod.DELETE, "/caveavin/paniers/*").hasRole("OWNER")

                        .requestMatchers(HttpMethod.GET, "/caveavin/regions").hasRole("OWNER")
                        .requestMatchers(HttpMethod.GET, "/caveavin/regions/*").hasRole("OWNER")

                        .anyRequest().denyAll();

        });

        // utilise "l'authentification basic" de http
        http.httpBasic(Customizer.withDefaults());

        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

}
