package com.SchoolManagementSystem.service.Impl;

import com.SchoolManagementSystem.entity.Course;
import com.SchoolManagementSystem.entity.Student;
import com.SchoolManagementSystem.repository.CourseRepository;
import com.SchoolManagementSystem.repository.StudentRepository;
import com.SchoolManagementSystem.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    CourseRepository courseRepository;
    StudentRepository studentRepository;

    @Override
    public Course getCourse(String courseName) {
        Course course = courseRepository.findById(courseName).orElseThrow(()-> new RuntimeException("There is no such a course"));
        return course ;
    }

    @Override
    public Course updateCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(String courseName) {
        courseRepository.deleteById(courseName);
    }

    @Override
    public Course deleteStudentFromCourse(String courseName, Integer studentId) {
        Optional<Course> optionalCourse = courseRepository.findById(courseName);
        Optional<Student> optionalStudent = studentRepository.findById(studentId);

        if(optionalCourse.isPresent() && optionalStudent.isPresent()){
            Student student = optionalStudent.get();
            courseRepository.deleteStudentFromCourse(courseName, student);
        }else {
            throw new RuntimeException("Course or Student not found");
        }

        return optionalCourse.get();
    }

    @Override
    public Course registerStudentForCourse(Integer studentId, String courseName) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("There is no sucha a student"));
        Course course = courseRepository.findById(courseName).orElseThrow(() -> new RuntimeException("There is no such a course"));

        course.getRegisteredStudents().add(student);
        student.getCourses().add(course);

        studentRepository.save(student);
        return courseRepository.save(course);
    }

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }
}
