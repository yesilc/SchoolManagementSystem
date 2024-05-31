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
    public Teacher createTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
        return teacher;
    }
}
