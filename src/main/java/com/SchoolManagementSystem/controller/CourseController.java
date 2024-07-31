package com.SchoolManagementSystem.controller;

import com.SchoolManagementSystem.DTO.CourseDTO;
import com.SchoolManagementSystem.DTO.StudentDTO;
import com.SchoolManagementSystem.entity.Course;
import com.SchoolManagementSystem.service.CourseService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value="/course")
public class CourseController {

    CourseService courseService;


    @GetMapping("/{courseId}")
    public ResponseEntity<CourseDTO> getCourseDTO(@PathVariable Long courseId){
        return new ResponseEntity<>(courseService.getCourseDTO(courseId), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<CourseDTO> createCourse(@RequestBody Course course){
        return new ResponseEntity<>(courseService.createCourse(course), HttpStatus.CREATED);
    }
    @PostMapping("/{courseId}/addStudent/{studentId}")
    public ResponseEntity<CourseDTO> addStudentToCourse(@PathVariable Long courseId, @PathVariable Long studentId){
        return new ResponseEntity<>(courseService.registerStudentForCourse(studentId,courseId), HttpStatus.OK);
    }
    @PutMapping("/updateCourse/{courseName}")
    public ResponseEntity<CourseDTO> updateCourse(@RequestBody Course course, @PathVariable Long courseId){
        Course existingCourse = courseService.getCourse(courseId);
        existingCourse.setCourseName(course.getCourseName());
        existingCourse.setTeacher(course.getTeacher());
        return new ResponseEntity<>(courseService.updateCourse(existingCourse), HttpStatus.OK);
    }
    @DeleteMapping("/deleteCourse/{courseName}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long courseId){
        courseService.deleteCourse(courseId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    /*@DeleteMapping("/{courseName}/deleteStudent/{studentId}")
    public ResponseEntity<Course> deleteStudentFromCourse(@PathVariable String courseName, @PathVariable Integer studentId){
        return new ResponseEntity<>(HttpStatus.OK);
    }*/
    @DeleteMapping("/{courseId}/deleteStudent/{studentId}")
    public ResponseEntity<Void> deleteStudentFromCourse(@PathVariable Long courseId, @PathVariable Long studentId){
        courseService.deleteStudentFromCourse(courseId,studentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/delete/{courseName}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long courseId){
        courseService.deleteCourse(courseId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/getAllCourses")
    public ResponseEntity<List<CourseDTO>> getAllCourses(){
        return new ResponseEntity<>(courseService.getAllCourses(),HttpStatus.OK);
    }
    @GetMapping("/getRegisteredStudents/{courseId}")
    public ResponseEntity<List<StudentDTO>> getRegisteredStudents(@PathVariable Long courseId){
        return new ResponseEntity<>(courseService.getStudentsFromCourse(courseId), HttpStatus.OK);
    }
    @GetMapping("/docAllCourseInfos")
    public void docAllCourseInfos(HttpServletResponse response) throws Exception{
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=courses.xls";
        response.setHeader(headerKey, headerValue);

        courseService.generateCourseInfos(response);
        response.flushBuffer();


    }

}
