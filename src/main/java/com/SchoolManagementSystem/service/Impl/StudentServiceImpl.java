package com.SchoolManagementSystem.service.Impl;

import com.SchoolManagementSystem.DTO.StudentDTO;
import com.SchoolManagementSystem.entity.Student;
import com.SchoolManagementSystem.mapper.StudentMapper;
import com.SchoolManagementSystem.repository.StudentRepository;
import com.SchoolManagementSystem.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    StudentRepository studentRepository;

    @Override
    public StudentDTO getStudentDTO(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("There is no such student with that ID"));
        return StudentMapper.mapToStudentDTO(student, new StudentDTO());
    }

    @Override
    public Student getStudent(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("There is no such a student"));
        return student;
    }

    @Override
    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    @Override
    public StudentDTO updateStudent(Student student) {
        Student student1 = studentRepository.save(student);
        return StudentMapper.mapToStudentDTO(student1, new StudentDTO());
    }

    @Override
    public StudentDTO createStudent(Student student) {
        Student student1 = studentRepository.save(student);
        return StudentMapper.mapToStudentDTO(student1, new StudentDTO());
    }


}
