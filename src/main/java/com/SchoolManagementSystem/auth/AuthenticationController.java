package com.SchoolManagementSystem.auth;

import com.SchoolManagementSystem.entity.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
   /* @PostMapping("/student/create")
    public ResponseEntity<AuthenticationResponse> createStudent(@RequestBody Student student){
        return new ResponseEntity<>(authenticationService.createStudent(student), HttpStatus.CREATED);
    }
    @PostMapping("/student/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticateStudent(@RequestBody AuthenticationRequest request{
        return new ResponseEntity<>(authenticationService.authenticateStudent(student), HttpStatus.OK);
    }*/



}
