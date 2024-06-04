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
    public Teacher getTeacher(Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() -> new RuntimeException("There is no such a teacher"));
        return teacher;
    }

    @Override
    public Teacher updateTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public void deleteTeacher(Long teacherId) {
        teacherRepository.deleteById(teacherId);
    }

    @Override
    public Teacher createTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
        return teacher;
    }
}
