package com.SchoolManagementSystem.service;

import com.SchoolManagementSystem.DTO.TeacherDTO;
import com.SchoolManagementSystem.entity.Teacher;

public interface TeacherService {
    Teacher getTeacher(Long teacherId);
    TeacherDTO createTeacher(Teacher teacher);
    TeacherDTO getTeacherDTO(Long teacherId);
    TeacherDTO updateTeacher(Teacher teacher);
    void deleteTeacher(Long teacherId);
}
