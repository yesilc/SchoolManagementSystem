package com.SchoolManagementSystem.controller;

import com.SchoolManagementSystem.DTO.StudentDTO;
import com.SchoolManagementSystem.entity.Student;
import com.SchoolManagementSystem.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/student")
public class StudentController {

    StudentService studentService;

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable Long id){
        return new ResponseEntity<>(studentService.getStudentDTO(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<StudentDTO> createStudent(@RequestBody Student student){
        return new ResponseEntity<>(studentService.createStudent(student),HttpStatus.CREATED);
    }
    @PutMapping("/updateStudent/{studentId}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long studentId, @RequestBody Student student){
       Student existingStudent = studentService.getStudent(studentId);
       existingStudent.setId(student.getId());
       existingStudent.setName(student.getName());
       existingStudent.setSurname(student.getSurname());

       return new ResponseEntity<>(studentService.updateStudent(existingStudent), HttpStatus.OK);
    }
    @DeleteMapping("/deleteStudent/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long studentId){
        studentService.deleteStudent(studentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
