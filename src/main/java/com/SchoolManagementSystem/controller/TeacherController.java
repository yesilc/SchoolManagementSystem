package com.SchoolManagementSystem.controller;

import com.SchoolManagementSystem.DTO.TeacherDTO;
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

    @GetMapping("/{teacherId}")
    public ResponseEntity<TeacherDTO> getTeacherDTO(@PathVariable Long teacherId){
        return new ResponseEntity<>(teacherService.getTeacherDTO(teacherId), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<TeacherDTO> createTeacher(@RequestBody Teacher teacher){
        return new ResponseEntity<>(teacherService.createTeacher(teacher), HttpStatus.CREATED);
    }
    @PutMapping("/updateTeacher/{teacherName}")
    public ResponseEntity<TeacherDTO> updateTeacher(@PathVariable Long teacherId, @RequestBody Teacher teacher){
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
