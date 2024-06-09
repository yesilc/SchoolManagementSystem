package com.SchoolManagementSystem.service;


import com.SchoolManagementSystem.DTO.GradeResponseDTO;
import com.SchoolManagementSystem.entity.Grade;

import java.util.List;

public interface GradeService {
    Grade getGrade(Long gradeId);
    Grade saveGrade(Grade grade);
    List<GradeResponseDTO> getGradesByStudentId(Long studentId);
    List<Grade> getGradesByCourseId(Long courseId);
    void deleteGrade(Long id);

}
