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
    public Student createStudent(Student student) {
        studentRepository.save(student);
        return student;
    }
}
