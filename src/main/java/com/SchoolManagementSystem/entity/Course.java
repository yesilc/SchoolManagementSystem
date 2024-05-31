package com.SchoolManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Course {

    @Id
    @Column(name = "course_name")
    private String courseName;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    //@JsonBackReference
    private Teacher teacher;
    @ManyToMany(mappedBy = "courses")
    private List<Student> registeredStudents;
}
