package com.SchoolManagementSystem.service.Impl;

import com.SchoolManagementSystem.entity.Teacher;
import com.SchoolManagementSystem.repository.TeacherRepository;
import com.SchoolManagementSystem.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    TeacherRepository teacherRepository;

    @Override
    public Teacher getTeacher(String teacherName) {
        Teacher teacher = teacherRepository.findById(teacherName).orElseThrow(() -> new RuntimeException("There is no such a teacher"));
        return teacher;
    }

    @Override
    public Teacher updateTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public void deleteTeacher(String teacherName) {
        teacherRepository.deleteById(teacherName);
    }

    @Override
    public Teacher createTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
        return teacher;
    }
}
