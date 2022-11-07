package mk.ukim.finki.wp.service.implementation;

import mk.ukim.finki.wp.model.Course;
import mk.ukim.finki.wp.model.Student;
import mk.ukim.finki.wp.repository.CourseRepository;
import mk.ukim.finki.wp.repository.StudentRepository;
import mk.ukim.finki.wp.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImplementation implements CourseService {


    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public CourseServiceImplementation(CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
        return courseRepository.findAllStudentsByCourse(courseId);
    }

    @Override
    public Course addStudentInCourse(String username, Long courseId) {
        return courseRepository.addStudentToCourse(studentRepository.findByUsername(username), courseRepository.findById(courseId).get());
    }

    @Override
    public List<Course> listAll() {
        return courseRepository.findAllCourses();
    }


    @Override
    public String courseNameFromId(Long courseId) {
        return courseRepository.findById(courseId).get().getName();
    }
}
