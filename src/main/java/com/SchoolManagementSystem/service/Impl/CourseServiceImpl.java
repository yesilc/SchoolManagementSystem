package com.SchoolManagementSystem.service.Impl;

import com.SchoolManagementSystem.entity.Course;
import com.SchoolManagementSystem.entity.Student;
import com.SchoolManagementSystem.repository.CourseRepository;
import com.SchoolManagementSystem.repository.StudentRepository;
import com.SchoolManagementSystem.service.CourseService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;
    private StudentRepository studentRepository;

    @Override
    public Course getCourse(Long courseId) {
        return courseRepository.findById(courseId).orElseThrow(()-> new RuntimeException("There is no such a course"));
    }

    @Override
    public Course updateCourse(Course course) {
        return courseRepository.save(course);
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
