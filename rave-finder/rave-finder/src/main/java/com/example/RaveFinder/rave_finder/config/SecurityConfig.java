package com.example.RaveFinder.rave_finder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())  // Disable CSRF for simplicity; consider enabling it for production
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/events/**").permitAll()  // Allow unauthenticated access to all /events endpoints
                .anyRequest().authenticated())  // Require authentication for all other endpoints
            .httpBasic(Customizer.withDefaults());  // Basic HTTP authentication

        return http.build();
    }
}
