package com.Prodiit.Prototype.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                /* .requestMatchers("/").permitAll()*/ // aca se estableceran los permisos de todas las rutas
                                /*.anyRequest().authenticated()*/ // aca pedira autentificacion para las demas rutas
                                .anyRequest().permitAll()  // Permitir todas las rutas
                )
                .csrf(csrf -> csrf.disable());  // Deshabilitar CSRF

        return http.build();
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}