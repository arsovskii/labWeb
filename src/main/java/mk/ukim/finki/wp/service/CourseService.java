package mk.ukim.finki.wp.service;

import mk.ukim.finki.wp.model.Course;
import mk.ukim.finki.wp.model.Student;
import mk.ukim.finki.wp.model.exceptions.CourseAlreadyExists;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<Student> listStudentsByCourse(Long courseId);
    Course addStudentInCourse(String username, Long courseId);
    List<Course> listAll();
    String courseNameFromId(Long courseId);
    Course addNewCourse(String name, String description, Long teacherId) throws CourseAlreadyExists;
    void deleteCourseById(Long id);
    Course editCourse(Long courseId, String name, String description, Long teacherId) throws CourseAlreadyExists;
    Optional<Course> getCourseFromId(Long id);
}
