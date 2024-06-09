package com.SchoolManagementSystem.service.Impl;

import com.SchoolManagementSystem.DTO.GradeResponseDTO;
import com.SchoolManagementSystem.entity.Grade;
import com.SchoolManagementSystem.mapper.GradeMapper;
import com.SchoolManagementSystem.repository.GradeRepository;
import com.SchoolManagementSystem.service.GradeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GradeServiceImpl implements GradeService {


    private GradeRepository gradeRepository;


    @Override
    public Grade getGrade(Long gradeId) {
        return gradeRepository.findById(gradeId).orElseThrow(()-> new RuntimeException("Grade not found"));
    }

    @Override
    public Grade saveGrade(Grade grade) {
        return gradeRepository.save(grade);
    }

    @Override
    public List<GradeResponseDTO> getGradesByStudentId(Long studentId) {
        List<Grade> grades = gradeRepository.findByStudentId(studentId);

        List<GradeResponseDTO> gradeResponseDtos = grades.stream()
                .map(grade -> GradeMapper.mapToGradeResponseDTO(grade, new GradeResponseDTO())).toList();
        return gradeResponseDtos;
    }

    @Override
    public List<Grade> getGradesByCourseId(Long courseId) {
        return gradeRepository.findByCourseId(courseId);
    }

    @Override
    public void deleteGrade(Long id) {
        gradeRepository.deleteById(id);
    }
}
