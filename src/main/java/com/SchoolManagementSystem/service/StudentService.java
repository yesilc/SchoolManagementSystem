package com.SchoolManagementSystem.service;

import com.SchoolManagementSystem.DTO.StudentDTO;
import com.SchoolManagementSystem.entity.Student;


public interface StudentService {
    StudentDTO createStudent(Student student);
    StudentDTO updateStudent(Student student);
    StudentDTO getStudentDTO(Long studentId);
    Student getStudent(Long studentId);
    void deleteStudent(Long studentId);

}
