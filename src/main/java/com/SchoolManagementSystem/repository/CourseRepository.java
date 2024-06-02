package com.SchoolManagementSystem.repository;

import com.SchoolManagementSystem.entity.Course;
import com.SchoolManagementSystem.entity.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CourseRepository extends JpaRepository<Course, String> {

    @Modifying
    @Transactional
    @Query("Delete FROM Course c WHERE c.id = :courseId AND :student MEMBER OF c.registeredStudents")
    Course deleteStudentFromCourse(@Param("courseName") String courseName, @Param("student") Student student);
}
