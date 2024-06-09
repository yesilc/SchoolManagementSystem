package com.SchoolManagementSystem.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CourseDTO {
    private Long courseId;
    private String courseName;
    private String teacherName;
    private List<StudentDTO> studentDTO = new ArrayList<>();
}
