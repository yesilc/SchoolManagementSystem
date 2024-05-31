package com.SchoolManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@Getter@Setter@ToString@NoArgsConstructor
public class Teacher {

    @Id
    private String name;
    private String surname;
    private String branch;
    private String email;
    @OneToMany(mappedBy = "teacher")
    @JsonBackReference
    private List<Course> courses;
}
