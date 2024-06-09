package com.SchoolManagementSystem.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StudentDTO {
    private Long id;
    private String name;
    private List<StudentGradeDTO> studentGradeDTOs;
}
