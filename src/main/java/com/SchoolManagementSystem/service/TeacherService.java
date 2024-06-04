package com.SchoolManagementSystem.service;

import com.SchoolManagementSystem.entity.Teacher;

public interface TeacherService {
    Teacher createTeacher(Teacher teacher);
    Teacher getTeacher(Long teacherId);
    Teacher updateTeacher(Teacher teacher);
    void deleteTeacher(Long teacherId);
}
