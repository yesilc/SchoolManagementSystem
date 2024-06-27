package com.SchoolManagementSystem.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.SchoolManagementSystem.entity.Permission.*;
import static com.SchoolManagementSystem.entity.Role.STUDENT;
import static com.SchoolManagementSystem.entity.Role.TEACHER;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private static final String[] URLS = {"/course/**", "/grade/**", "/student/**", "/teacher/**"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> {
                    request.requestMatchers("/auth/**").permitAll()
                                    .requestMatchers(URLS).hasAnyRole(TEACHER.name(), STUDENT.name())
                                    .requestMatchers(GET, URLS).hasAnyAuthority(TEACHER_READ.name(), STUDENT_READ.name())
                                    .requestMatchers(POST, URLS).hasAnyAuthority(TEACHER_CREATE.name())
                                    .requestMatchers(PUT, URLS).hasAnyAuthority(TEACHER_UPDATE.name())
                                    .requestMatchers(DELETE, URLS).hasAnyAuthority(TEACHER_DELETE.name());
                    request.anyRequest().authenticated();
                })
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
