package mk.ukim.finki.wp.repository;

import mk.ukim.finki.wp.bootstrap.DataHolder;
import mk.ukim.finki.wp.model.Course;
import mk.ukim.finki.wp.model.Student;
import mk.ukim.finki.wp.model.exceptions.CourseDoenstExistException;
import mk.ukim.finki.wp.model.exceptions.NotEnoughInfoForNewStudentException;
import mk.ukim.finki.wp.model.exceptions.StudentAlreadyAttendingCourseException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CourseRepository {

    public List<Course> findAllCourses() {
        return DataHolder.courses;
    }


    public Optional<Course> findById(Long courseId) {
        return DataHolder.courses.stream().filter(i -> i.getCourseId().equals(courseId)).findFirst();
    }

    public List<Student> findAllStudentsByCourse(Long courseId) {
        try {
            if (findById(courseId).isEmpty()) {
                throw new CourseDoenstExistException();
            }
            return findById(courseId).get().getStudents();
        } catch (Exception e) {
            return null;
        }

    }

    public Course addStudentToCourse(Student student, Course course) throws CourseDoenstExistException, StudentAlreadyAttendingCourseException {

        if (findById(course.getCourseId()).isEmpty()) {
            throw new CourseDoenstExistException();
        }

        if (course.getStudents().stream().anyMatch(i -> i.getUsername().equals(student.getUsername()))) {
            throw new StudentAlreadyAttendingCourseException();
        }
        findById(course.getCourseId()).get().getStudents().add(student);

        return course;


    }

}
