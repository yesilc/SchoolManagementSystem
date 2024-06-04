package com.SchoolManagementSystem.repository;

import com.SchoolManagementSystem.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    /*@Modifying
    @Transactional
    @Query("UPDATE FROM Course c SET c.registeredStudents = (SELECT s FROM Student s WHERE s.id != :studentId" +
            "AND s MEMBER OF c.registeredStudents) WHERE c.courseName = :courseName")
    void deleteStudentFromCourse(@Param("courseName") String courseName, @Param("studentId") Integer studentId);*/

    @Query("SELECT c FROM Course c JOIN FETCH c.registeredStudents WHERE c.courseName = :courseName")
    Optional<Course> fetchCourseWithItsStudents(@Param("courseName") String courseName);
}
