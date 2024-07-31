package com.SchoolManagementSystem.service.Impl;

import com.SchoolManagementSystem.DTO.CourseDTO;
import com.SchoolManagementSystem.DTO.StudentDTO;
import com.SchoolManagementSystem.entity.Course;
import com.SchoolManagementSystem.entity.Student;
import com.SchoolManagementSystem.mapper.CourseMapper;
import com.SchoolManagementSystem.mapper.StudentMapper;
import com.SchoolManagementSystem.repository.CourseRepository;
import com.SchoolManagementSystem.repository.StudentRepository;
import com.SchoolManagementSystem.service.CourseService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;
    private StudentRepository studentRepository;
    private CacheManager cacheManager;

    @Override
    public Course getCourse(Long courseId) {
        return courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("There is no such a course"));
    }

    @Override
    @Cacheable(cacheNames = "course_id", key = "#root.methodName + #courseId")
    public CourseDTO getCourseDTO(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(()-> new RuntimeException("There is no such a course"));
        return CourseMapper.mapToCourseDTO(course, new CourseDTO());
    }

    @Override
    @CachePut(cacheNames = "course_id", key = "getCourseDTO + #course.courseId")
    public CourseDTO updateCourse(Course course) {
        Course course1 = courseRepository.save(course);
        return CourseMapper.mapToCourseDTO(course1, new CourseDTO());
    }

    @Override
    public void generateCourseInfos(HttpServletResponse response) throws Exception {
        List<Course> courses = courseRepository.findAll();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Courses Info");
        HSSFRow row = sheet.createRow(0);

        row.createCell(0).setCellValue("CourseId");
        row.createCell(1).setCellValue("CourseName");
        row.createCell(2).setCellValue("TeacherName");
        row.createCell(3).setCellValue("RegisteredStudents");

        int dataRowIndex = 1;

        for (Course course : courses){
            HSSFRow dataRow = sheet.createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(course.getCourseId());
            dataRow.createCell(1).setCellValue(course.getCourseName());
            dataRow.createCell(2).setCellValue(course.getTeacher().getName() +" "+ course.getTeacher().getSurname());
            dataRow.createCell(3).setCellValue(course.getRegisteredStudents().toArray().length);
            dataRowIndex++;
        }
        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();

    }

    @Override
    @CacheEvict(cacheNames = {"course_id, courses"}, allEntries = true)
    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }


    @Override
    @CacheEvict(cacheNames = {"course_id", "courses"}, allEntries = true)
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
    @CachePut(cacheNames = "course_id", key = "getCourseDTO + #courseId")
    public CourseDTO registerStudentForCourse(Long studentId, Long courseId) {

        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("There is no sucha a student"));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("There is no such a course"));

        course.getRegisteredStudents().add(student);
        student.getCourses().add(course);
        studentRepository.save(student);
        Course course1 = courseRepository.save(course);


        cacheManager.getCache("courses").put("getAllCourses", courseRepository.findAll().stream()
                .map(c -> CourseMapper.mapToCourseDTO(c, new CourseDTO()))
                .collect(Collectors.toList())
        );


        return CourseMapper.mapToCourseDTO(course1, new CourseDTO());
    }

    @Override
    @Cacheable(cacheNames = "studentsFromCourse", key = "#root.methodName + #courseId", unless = "#result == null || #result.isEmpty()")
    public List<StudentDTO> getStudentsFromCourse(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(()-> new RuntimeException("There is no such a course"));
        List<StudentDTO> studentDTOs = course.getRegisteredStudents().stream()
                .map(student -> StudentMapper.mapToStudentDTO(student, new StudentDTO())).toList();
        return studentDTOs;
    }

    @Override
    @Cacheable(cacheNames = "courses", key = "#root.methodName", unless = "#result == null")
    public List<CourseDTO> getAllCourses() {
        List<CourseDTO> courseDTOs = courseRepository.findAll().stream()
                .map(course -> CourseMapper.mapToCourseDTO(course, new CourseDTO())).toList();
        return courseDTOs;
    }

    @Override
    @CachePut(cacheNames = "getAllCourses", unless = "#result == null || #result.isEmpty()")
    public CourseDTO createCourse(Course course) {
        Course course1 = courseRepository.save(course);
        return CourseMapper.mapToCourseDTO(course1, new CourseDTO());
    }
}
