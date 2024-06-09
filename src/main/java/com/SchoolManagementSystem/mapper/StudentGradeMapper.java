package com.SchoolManagementSystem.mapper;

import com.SchoolManagementSystem.DTO.StudentGradeDTO;
import com.SchoolManagementSystem.entity.Grade;

public class StudentGradeMapper {
    public static StudentGradeDTO mapToStudentGradeDTO(Grade grade, StudentGradeDTO studentGradeDTO){
        studentGradeDTO.setCourseName(grade.getCourse().getCourseName());
        studentGradeDTO.setGrade(grade.getGrade());
        return studentGradeDTO;
    }
}
