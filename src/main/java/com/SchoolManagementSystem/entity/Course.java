package com.SchoolManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Course{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    @Column(name = "course_name")
    private String courseName;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    //@JsonBackReference
    private Teacher teacher;
    @ManyToMany(mappedBy = "courses", fetch = FetchType.EAGER)
    private List<Student> registeredStudents = new ArrayList<>();
    @OneToMany(mappedBy = "course")
    @JsonBackReference
    private List<Grade> grades = new ArrayList<>();
}
