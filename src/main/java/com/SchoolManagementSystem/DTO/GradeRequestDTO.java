package com.SchoolManagementSystem.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GradeRequestDTO {
    private Long studentId;
    private Long courseId;
    private Integer grade;
}
