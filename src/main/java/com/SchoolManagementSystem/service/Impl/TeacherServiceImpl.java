package com.SchoolManagementSystem.service.Impl;

import com.SchoolManagementSystem.DTO.TeacherDTO;
import com.SchoolManagementSystem.entity.Teacher;
import com.SchoolManagementSystem.mapper.TeacherMapper;
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
        return teacherRepository.findById(teacherId).orElseThrow(()-> new RuntimeException("There is no such a teacher"));
    }

    @Override
    public TeacherDTO getTeacherDTO(Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() -> new RuntimeException("There is no such a teacher"));
        return TeacherMapper.mapToTeacherDTO(teacher, new TeacherDTO());
    }

    @Override
    public TeacherDTO updateTeacher(Teacher teacher) {
        Teacher teacher1 = teacherRepository.save(teacher);
        return TeacherMapper.mapToTeacherDTO(teacher1, new TeacherDTO());
    }

    @Override
    public void deleteTeacher(Long teacherId) {
        teacherRepository.deleteById(teacherId);
    }

    @Override
    public TeacherDTO createTeacher(Teacher teacher) {
        Teacher teacher1 = teacherRepository.save(teacher);
        return TeacherMapper.mapToTeacherDTO(teacher1, new TeacherDTO());
    }
}
