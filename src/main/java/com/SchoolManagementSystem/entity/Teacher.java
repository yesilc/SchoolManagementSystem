package com.SchoolManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@Getter@Setter@ToString@NoArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherId;
    private String name;
    private String surname;
    private String branch;
    private String email;
    @OneToMany(mappedBy = "teacher")
    @JsonBackReference
    private List<Course> courses = new ArrayList<>();
}
