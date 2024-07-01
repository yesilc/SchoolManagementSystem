package com.SchoolManagementSystem.auth;

import com.SchoolManagementSystem.entity.Teacher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/teacher/create")
    public ResponseEntity<AuthenticationResponse> createTeacher(@RequestBody Teacher teacher){
        return new ResponseEntity<>(authenticationService.createTeacher(teacher), HttpStatus.CREATED);
    }

    @PostMapping("/teacher/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticateTeacher(@RequestBody AuthenticationRequest request){
        return new ResponseEntity<>(authenticationService.authenticateTeacher(request), HttpStatus.OK);
    }
    @PostMapping("/student/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticateStudent(@RequestBody AuthenticationRequest request){
        return new ResponseEntity<>(authenticationService.authenticateStudent(request), HttpStatus.OK);
    }
    @PostMapping("/refresh-token")
    public ResponseEntity<Void> refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        authenticationService.refreshToken(request, response);
        return null;
    }


}
