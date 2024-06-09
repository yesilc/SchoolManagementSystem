package com.SchoolManagementSystem.mapper;

import com.SchoolManagementSystem.DTO.CourseDTO;
import com.SchoolManagementSystem.DTO.StudentDTO;
import com.SchoolManagementSystem.entity.Course;

public class CourseMapper {
    public static CourseDTO mapToCourseDTO(Course course, CourseDTO courseDTO){
        courseDTO.setCourseId(course.getCourseId());
        courseDTO.setCourseName(course.getCourseName());
        courseDTO.setTeacherName(course.getTeacher().getName() +" "+ course.getTeacher().getSurname());
        courseDTO.setStudentDTO(course.getRegisteredStudents().stream()
                .map(student -> StudentMapper.mapToStudentDTO(student, new StudentDTO())).toList());
        return courseDTO;
    }
}
