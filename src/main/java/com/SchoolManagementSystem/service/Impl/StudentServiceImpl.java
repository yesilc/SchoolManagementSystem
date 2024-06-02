package com.SchoolManagementSystem.service.Impl;

import com.SchoolManagementSystem.entity.Student;
import com.SchoolManagementSystem.repository.StudentRepository;
import com.SchoolManagementSystem.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    StudentRepository studentRepository;

    @Override
    public Student getStudent(Integer studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("There is no such student with that ID"));
        return student;
    }

    @Override
    public void deleteStudent(Integer studentId) {
        studentRepository.deleteById(studentId);
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student createStudent(Student student) {
        studentRepository.save(student);
        return student;
    }


}
