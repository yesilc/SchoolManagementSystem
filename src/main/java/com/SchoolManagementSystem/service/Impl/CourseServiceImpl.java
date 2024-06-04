package com.SchoolManagementSystem.service.Impl;

import com.SchoolManagementSystem.entity.Course;
import com.SchoolManagementSystem.entity.Student;
import com.SchoolManagementSystem.repository.CourseRepository;
import com.SchoolManagementSystem.repository.StudentRepository;
import com.SchoolManagementSystem.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    CourseRepository courseRepository;
    StudentRepository studentRepository;

    @Override
    public Course getCourse(Long courseId) {
        return courseRepository.findById(courseId).orElseThrow(()-> new RuntimeException("There is no such a course"));
    }

    @Override
    public Course updateCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }


    @Override
    public void deleteStudentFromCourse(Long courseId, Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("There is no such a Student"));
        for(Course course : student.getCourses()){
            if (course.getCourseId() == courseId){
                student.getCourses().remove(course);
                studentRepository.save(student);
                return;
            } else {
                throw new RuntimeException("There is no such student registered for this course");
            }
        }
    }


    @Override
    public Course registerStudentForCourse(Long studentId, Long courseId) {

        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("There is no sucha a student"));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("There is no such a course"));

        course.getRegisteredStudents().add(student);
        student.getCourses().add(course);

        studentRepository.save(student);
        return courseRepository.save(course);
    }

    @Override
    public List<Student> getStudentsFromCourse(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(()-> new RuntimeException("There is no such a course"));
        return course.getRegisteredStudents();
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }
}
