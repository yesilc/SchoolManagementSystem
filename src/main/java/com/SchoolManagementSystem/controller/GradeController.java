package com.SchoolManagementSystem.controller;

import com.SchoolManagementSystem.DTO.GradeRequestDTO;
import com.SchoolManagementSystem.DTO.GradeResponseDTO;
import com.SchoolManagementSystem.entity.Course;
import com.SchoolManagementSystem.entity.Grade;
import com.SchoolManagementSystem.entity.Student;
import com.SchoolManagementSystem.repository.CourseRepository;
import com.SchoolManagementSystem.repository.StudentRepository;
import com.SchoolManagementSystem.service.GradeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grade")
@AllArgsConstructor
public class GradeController {
    private GradeService gradeService;
    private StudentRepository studentRepository;
    private CourseRepository courseRepository;


    @PostMapping("/createGrade")
    public ResponseEntity<Grade> createGrade(@RequestBody GradeRequestDTO gradeRequestDTO){
        Student student = studentRepository.findById(gradeRequestDTO.getStudentId()).orElseThrow(()-> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(gradeRequestDTO.getCourseId()).orElseThrow(()-> new RuntimeException("Course not found"));

        Grade grade = new Grade();
        grade.setStudent(student);
        grade.setCourse(course);
        grade.setGrade(gradeRequestDTO.getGrade());

        return new ResponseEntity<>(gradeService.saveGrade(grade), HttpStatus.OK);
    }
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<GradeResponseDTO>> getGradesByStudentId(@PathVariable Long studentId){
        return new ResponseEntity<>(gradeService.getGradesByStudentId(studentId), HttpStatus.OK);
    }
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Grade>> getGradesByCourseId(@PathVariable Long courseId){
        return new ResponseEntity<>(gradeService.getGradesByCourseId(courseId), HttpStatus.OK);
    }
    @GetMapping("/{gradeId}")
    public ResponseEntity<Grade> getGradeById(@PathVariable Long gradeId){
        return new ResponseEntity<>(gradeService.getGrade(gradeId), HttpStatus.OK);
    }
}
