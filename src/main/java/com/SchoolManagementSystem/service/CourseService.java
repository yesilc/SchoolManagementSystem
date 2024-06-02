package com.SchoolManagementSystem.service;

import com.SchoolManagementSystem.entity.Course;

public interface CourseService {

     Course getCourse(String courseName);
     Course createCourse(Course course);
     Course registerStudentForCourse(Integer studentId, String courseName);
     Course updateCourse(Course course);
     void deleteCourse(String courseName);
     Course deleteStudentFromCourse(String courseName, Integer studentId);
}
