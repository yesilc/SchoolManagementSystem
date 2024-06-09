package com.SchoolManagementSystem.mapper;

import com.SchoolManagementSystem.DTO.GradeResponseDTO;
import com.SchoolManagementSystem.entity.Grade;

public class GradeMapper {
    public static GradeResponseDTO mapToGradeResponseDTO(Grade grade, GradeResponseDTO gradeResponseDTO){
        gradeResponseDTO.setCourseName(grade.getCourse().getCourseName());
        gradeResponseDTO.setStudentName(grade.getStudent().getName() +" "+ grade.getStudent().getSurname());
        gradeResponseDTO.setGrade(grade.getGrade());
        return gradeResponseDTO;
    }
}
