package com.SchoolManagementSystem.repository;

import com.SchoolManagementSystem.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, String> {
}
