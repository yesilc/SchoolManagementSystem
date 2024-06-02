package com.SchoolManagementSystem.service;

import com.SchoolManagementSystem.entity.Student;


public interface StudentService {
    Student createStudent(Student student);
    Student updateStudent(Student student);
    Student getStudent(Integer studentId);
    void deleteStudent(Integer studentId);

}
