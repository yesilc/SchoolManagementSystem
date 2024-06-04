package com.SchoolManagementSystem.service;

import com.SchoolManagementSystem.entity.Course;
import com.SchoolManagementSystem.entity.Student;

import java.util.List;

public interface CourseService {

     Course getCourse(Long courseId);
     Course createCourse(Course course);
     Course registerStudentForCourse(Long studentId, Long courseId);
     Course updateCourse(Course course);
     void deleteCourse(Long courseId);
     void deleteStudentFromCourse(Long courseId, Long studentId);
     List<Course> getAllCourses();
     List<Student> getStudentsFromCourse(Long courseId);
}
