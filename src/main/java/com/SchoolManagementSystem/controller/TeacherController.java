package com.SchoolManagementSystem.controller;

import com.SchoolManagementSystem.entity.Teacher;
import com.SchoolManagementSystem.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/teacher")
public class TeacherController {

    TeacherService teacherService;

    @GetMapping("/{teacherName}")
    public ResponseEntity<Teacher> getTeacher(@PathVariable Long teacherId){
        return new ResponseEntity<>(teacherService.getTeacher(teacherId), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher){
        return new ResponseEntity<>(teacherService.createTeacher(teacher), HttpStatus.CREATED);
    }
    @PutMapping("/updateTeacher/{teacherName}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable Long teacherId, @RequestBody Teacher teacher){
        Teacher existingTeacher = teacherService.getTeacher(teacherId);
        existingTeacher.setName(teacher.getName());
        existingTeacher.setSurname(teacher.getSurname());
        existingTeacher.setEmail(teacher.getEmail());
        existingTeacher.setBranch(teacher.getBranch());

        return new ResponseEntity<>(teacherService.updateTeacher(existingTeacher),HttpStatus.OK);
    }
    @DeleteMapping("/deleteTeacher/{teacherName}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long teacherId){
        teacherService.deleteTeacher(teacherId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
