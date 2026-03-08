package com.empresamoviles.mobile.config;

import org.apache.catalina.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth

                // Consola H2 — solo para desarrollo
                .requestMatchers("/h2-console/**").permitAll()

                // Swagger / OpenAPI — público
                .requestMatchers(
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/v3/api-docs/**"
                ).permitAll()

                // Endpoints GET públicos — GUEST y ADMIN
                .requestMatchers(HttpMethod.GET, "/api/mobiles/**").hasAnyRole("GUEST", "ADMIN")

                // Endpoints de escritura — solo ADMIN
                .requestMatchers(HttpMethod.POST, "/api/mobiles/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/mobiles/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/mobiles/**").hasRole("ADMIN")

                // Cualquier otra petición requiere autenticación
                .anyRequest().authenticated()
            )
            // Compatibilidad con H2 console (frames)
            .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }
	
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
	 
	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }


}
