package com.SchoolManagementSystem.auth;


import com.SchoolManagementSystem.config.JwtService;
import com.SchoolManagementSystem.entity.Teacher;
import com.SchoolManagementSystem.repository.StudentRepository;
import com.SchoolManagementSystem.repository.TeacherRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse createTeacher(Teacher teacher) {
        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
        teacherRepository.save(teacher);
        var jwtToken = jwtService.generateToken(teacher);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }
    public AuthenticationResponse authenticateStudent(AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = studentRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new RuntimeException("There is no such a student"));
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthenticationResponse authenticateTeacher(AuthenticationRequest request){
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                  request.getEmail(),
                  request.getPassword()
            )
        );
        var user = teacherRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new RuntimeException("There is no such a teacher"));
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if(userEmail != null){
            var userDetails =
                    this.teacherRepository.findByEmail(userEmail).equals(Optional.empty()) ?
                            this.studentRepository.findByEmail(userEmail).get() : this.teacherRepository.findByEmail(userEmail).get();
            if(jwtService.isTokenValid(refreshToken, userDetails)){
                var accessToken = jwtService.generateToken(userDetails);
                var authResponse = AuthenticationResponse.builder()
                        .refreshToken(refreshToken)
                        .accessToken(accessToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }


}
