package com.SchoolManagementSystem.mapper;

import com.SchoolManagementSystem.DTO.TeacherDTO;
import com.SchoolManagementSystem.entity.Teacher;

public class TeacherMapper {
    public static TeacherDTO mapToTeacherDTO(Teacher teacher,TeacherDTO teacherDTO){
        teacherDTO.setTeacherId(teacher.getTeacherId());
        teacherDTO.setName(teacher.getName() +" "+ teacher.getSurname());
        teacherDTO.setBranch(teacher.getBranch());
        teacherDTO.setEmail(teacher.getEmail());
        teacherDTO.setCourses(teacher.getCourses().stream().map(course -> course.getCourseName()).toList());
        return teacherDTO;
    }
}
