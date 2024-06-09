package com.SchoolManagementSystem.repository;

import com.SchoolManagementSystem.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {

    @Query("SELECT g FROM Grade g WHERE g.student.id = :studentId")
    List<Grade> findByStudentId(@Param("studentId") Long studentId);
    @Query("SELECT g FROM Grade g WHERE g.course.id = :courseId")
    List<Grade> findByCourseId(@Param("courseId") Long courseId);


}
