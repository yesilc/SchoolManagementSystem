package com.SchoolManagementSystem.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TeacherDTO {
    private Long teacherId;
    private String name;
    private String branch;
    private String email;
    private List<String> courses;
}
