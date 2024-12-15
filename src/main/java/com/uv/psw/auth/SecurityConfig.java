package com.uv.psw.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final AuthService authService;
    private final JwtRequestFilter jwtRequestFilter;

    public SecurityConfig(@Lazy AuthService authService, @Lazy JwtRequestFilter jwtRequestFilter) {
        this.authService = authService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    /**
     * Bean para cifrar contraseñas usando BCrypt.
     *
     * @return Instancia de PasswordEncoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configuración de la cadena de seguridad.
     *
     * @param http Instancia de HttpSecurity.
     * @return SecurityFilterChain configurado.
     * @throws Exception Excepción en caso de error.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/api/auth/verify","/api/auth/register", "/api/auth/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    /**
     * Configuración del AuthenticationManager para manejar autenticaciones.
     *
     * @param http            Instancia de HttpSecurity.
     * @param passwordEncoder Instancia de PasswordEncoder.
     * @return AuthenticationManager configurado.
     * @throws Exception Excepción en caso de error.
     */
    @Bean
    public AuthenticationManager authManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(authService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }
}
