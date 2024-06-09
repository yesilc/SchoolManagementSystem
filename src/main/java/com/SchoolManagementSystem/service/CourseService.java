package com.SchoolManagementSystem.service;

import com.SchoolManagementSystem.DTO.CourseDTO;
import com.SchoolManagementSystem.DTO.StudentDTO;
import com.SchoolManagementSystem.entity.Course;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public interface CourseService {

     CourseDTO getCourseDTO(Long courseId);
     Course getCourse(Long courseId);
     CourseDTO createCourse(Course course);
     CourseDTO registerStudentForCourse(Long studentId, Long courseId);
     CourseDTO updateCourse(Course course);
     void deleteCourse(Long courseId);
     void deleteStudentFromCourse(Long courseId, Long studentId);
     List<CourseDTO> getAllCourses();
     List<StudentDTO> getStudentsFromCourse(Long courseId);
     void generateCourseInfos(HttpServletResponse response) throws Exception;
}
