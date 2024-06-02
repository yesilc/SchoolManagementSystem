package com.SchoolManagementSystem.service;

import com.SchoolManagementSystem.entity.Teacher;

public interface TeacherService {
    Teacher createTeacher(Teacher teacher);
    Teacher getTeacher(String teacherName);
    Teacher updateTeacher(Teacher teacher);
    void deleteTeacher(String teacherName);
}
