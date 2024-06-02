package com.SchoolManagementSystem.controller;

import com.SchoolManagementSystem.entity.Course;
import com.SchoolManagementSystem.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value="/course")
public class CourseController {

    CourseService courseService;

    @GetMapping("/{courseName}")
    public ResponseEntity<Course> getCourse(@PathVariable String courseName){
        return new ResponseEntity<>(courseService.getCourse(courseName), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Course> createCourse(@RequestBody Course course){
        return new ResponseEntity<>(courseService.createCourse(course), HttpStatus.CREATED);
    }
    @PostMapping("/{courseName}/addStudent/{studentId}")
    public ResponseEntity<Course> addStudentToCourse(@PathVariable String courseName, @PathVariable Integer studentId){
        return new ResponseEntity<>(courseService.registerStudentForCourse(studentId,courseName), HttpStatus.OK);
    }
    @PutMapping("/updateCourse/{courseName}")
    public ResponseEntity<Course> updateCourse(@RequestBody Course course, @PathVariable String courseName){
        Course existingCourse = courseService.getCourse(courseName);
        existingCourse.setCourseName(course.getCourseName());
        existingCourse.setTeacher(course.getTeacher());
        return new ResponseEntity<>(courseService.updateCourse(existingCourse), HttpStatus.OK);
    }
    @DeleteMapping("/deleteCourse/{courseName}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable String courseName){
        courseService.deleteCourse(courseName);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{courseName}/deleteStudent/{studentId}")
    public ResponseEntity<Course> deleteStudentFromCourse(@PathVariable String courseName, @PathVariable Integer studentId){
        return new ResponseEntity<>(courseService.deleteStudentFromCourse(courseName, studentId), HttpStatus.OK);
    }

}
