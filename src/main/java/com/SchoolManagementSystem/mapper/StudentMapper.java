package com.SchoolManagementSystem.mapper;

import com.SchoolManagementSystem.DTO.StudentDTO;
import com.SchoolManagementSystem.DTO.StudentGradeDTO;
import com.SchoolManagementSystem.entity.Student;

public class StudentMapper {
    public static StudentDTO mapToStudentDTO(Student student, StudentDTO studentDTO){
        studentDTO.setId(student.getId());
        studentDTO.setName(student.getName()+" "+student.getSurname());
        studentDTO.setStudentGradeDTOs(student.getGrade().stream()
                .map(grade -> StudentGradeMapper.mapToStudentGradeDTO(grade, new StudentGradeDTO())).toList());
        return studentDTO;
    }
}
