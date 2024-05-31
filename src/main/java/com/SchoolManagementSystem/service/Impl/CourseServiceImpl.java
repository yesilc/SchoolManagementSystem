package com.SchoolManagementSystem.service.Impl;

import com.SchoolManagementSystem.entity.Course;
import com.SchoolManagementSystem.repository.CourseRepository;
import com.SchoolManagementSystem.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    CourseRepository courseRepository;

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }
}
