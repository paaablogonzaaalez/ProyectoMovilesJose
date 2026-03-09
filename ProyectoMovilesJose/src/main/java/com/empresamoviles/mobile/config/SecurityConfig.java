package com.empresamoviles.mobile.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuración de seguridad de la API.
 * Utiliza HTTP Basic Authentication con dos roles:
 *   - ADMIN: acceso completo (GET + POST + PUT + DELETE)
 *   - GUEST: solo lectura (GET)
 * Los usuarios se almacenan en memoria para simplificar el despliegue.
 * Habilita @PreAuthorize para protección a nivel de método.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    /**
     * Configura las reglas de autorización HTTP y el mecanismo de autenticación.
     * La consola H2 y Swagger están permitidos sin autenticación.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
               
                .requestMatchers("/h2-console/**").permitAll()
          
                .requestMatchers("/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**").permitAll()
        
                .requestMatchers(HttpMethod.GET, "/api/v1/mobiles/**").hasAnyRole("GUEST", "ADMIN")

                .requestMatchers(HttpMethod.POST, "/api/v1/mobiles/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/v1/mobiles/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/mobiles/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    /**
     * Define los usuarios en memoria.
     * admin / admin → rol ADMIN (acceso completo)
     * guest / guest → rol GUEST (solo lectura)
     */
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN")
                .build();

        UserDetails guest = User.builder()
                .username("guest")
                .password(passwordEncoder.encode("guest"))
                .roles("GUEST")
                .build();

        return new InMemoryUserDetailsManager(admin, guest);
    }

    /**
     * Bean del encoder de contraseñas.
     * Usa BCrypt para un cifrado seguro.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}