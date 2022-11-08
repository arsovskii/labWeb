package mk.ukim.finki.wp.service.implementation;

import mk.ukim.finki.wp.model.Course;
import mk.ukim.finki.wp.model.Student;
import mk.ukim.finki.wp.model.exceptions.CourseAlreadyExists;
import mk.ukim.finki.wp.model.exceptions.TeacherNotFound;
import mk.ukim.finki.wp.repository.CourseRepository;
import mk.ukim.finki.wp.repository.StudentRepository;
import mk.ukim.finki.wp.repository.TeacherRepository;
import mk.ukim.finki.wp.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImplementation implements CourseService {


    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public CourseServiceImplementation(CourseRepository courseRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
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

    @Override
    public Course addNewCourse(String name, String description, Long teacherId) throws CourseAlreadyExists {
        return courseRepository.addCourse(new Course(name, description, new ArrayList<>(), teacherRepository.findById(teacherId).orElseThrow(TeacherNotFound::new)));
    }

    @Override
    public void deleteCourseById(Long id) {
        courseRepository.deleteCourseById(id);
    }

    @Override
    public Course editCourse(Long courseId, String name, String description, Long teacherId) throws CourseAlreadyExists {
        return courseRepository.editCourse(courseId, name, description, teacherRepository.findById(teacherId).orElseThrow(TeacherNotFound::new));
    }

    public Optional<Course> getCourseFromId(Long id){
        return courseRepository.findById(id);
    }
}
