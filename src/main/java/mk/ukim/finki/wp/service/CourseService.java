package mk.ukim.finki.wp.service;

import mk.ukim.finki.wp.model.Course;
import mk.ukim.finki.wp.model.Grade;
import mk.ukim.finki.wp.model.Student;
import mk.ukim.finki.wp.model.exceptions.CourseAlreadyExists;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CourseService {
    Set<Student> listStudentsByCourse(Long courseId);
    void addStudentInCourse(String username, Long courseId);
    List<Course> listAll();
    String courseNameFromId(Long courseId);
    Course addNewCourse(String name, String description, Long teacherId) throws CourseAlreadyExists;
    void deleteCourseById(Long id);
    Course editCourse(Long courseId, String name, String description, Long teacherId) throws CourseAlreadyExists;
    Optional<Course> getCourseFromId(Long id);
    List<Grade> getGradesForCourse(Long course);
    void addGrade(Character Grade, LocalDateTime time, Long courseId, String username);
}
