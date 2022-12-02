package mk.ukim.finki.wp.service.implementation;

import mk.ukim.finki.wp.model.Course;
import mk.ukim.finki.wp.model.Grade;
import mk.ukim.finki.wp.model.Student;
import mk.ukim.finki.wp.model.exceptions.CourseAlreadyExists;
import mk.ukim.finki.wp.model.exceptions.CourseDoesntExistException;
import mk.ukim.finki.wp.model.exceptions.TeacherNotFound;
import mk.ukim.finki.wp.repository.jpa.CourseRepositoryDB;
import mk.ukim.finki.wp.repository.jpa.GradeRepositoryDB;
import mk.ukim.finki.wp.repository.jpa.StudentRepositoryDB;
import mk.ukim.finki.wp.repository.jpa.TeacherRepositoryDB;
import mk.ukim.finki.wp.service.CourseService;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class CourseServiceImplementation implements CourseService {


    private final CourseRepositoryDB courseRepository;
    private final StudentRepositoryDB studentRepository;
    private final TeacherRepositoryDB teacherRepository;
    private final GradeRepositoryDB gradeRepository;
    public CourseServiceImplementation(CourseRepositoryDB courseRepository, StudentRepositoryDB studentRepository, TeacherRepositoryDB teacherRepository, GradeRepositoryDB gradeRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.gradeRepository = gradeRepository;
    }

    @Override

    public Set<Student> listStudentsByCourse(Long courseId) {

        return courseRepository.findById(courseId).orElseThrow(CourseDoesntExistException::new).getStudents();
    }

    @Override
    @Transactional
    public void addStudentInCourse(String username, Long courseId) {
        courseRepository.findById(courseId).orElseThrow(CourseDoesntExistException::new).getStudents().add(studentRepository.findByUsername(username));
    }

    @Override
    public List<Course> listAll() {
        return courseRepository.findAll();
    }


    @Override
    public String courseNameFromId(Long courseId) {
        return courseRepository.findById(courseId).orElseThrow(CourseDoesntExistException::new).getName();
    }

    @Override
    public Course addNewCourse(String name, String description, Long teacherId) throws CourseAlreadyExists {
        return courseRepository.save(new Course(name, description, new HashSet<>(), teacherRepository.findById(teacherId).orElseThrow(TeacherNotFound::new)));
    }

    @Override
    public void deleteCourseById(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Course editCourse(Long courseId, String name, String description, Long teacherId) throws CourseAlreadyExists {
        Course course = courseRepository.findById(courseId).orElseThrow(CourseDoesntExistException::new);
        course.setName(name);
        course.setDescription(description);
        course.setTeacher(teacherRepository.getReferenceById(teacherId));
        return course;
        //return courseRepository.editCourse(courseId, name, description, teacherRepository.findById(teacherId).orElseThrow(TeacherNotFound::new));
    }

    public Optional<Course> getCourseFromId(Long id) {
        return courseRepository.findById(id);
    }

    public List<Grade> getGradesForCourse(Long courseId){
        return gradeRepository.getGradesByCourse(courseRepository.findById(courseId).orElseThrow(CourseDoesntExistException::new));
    }

    @Override
    public void addGrade(Character grade, LocalDateTime time, Long courseId, String username) {
        gradeRepository.save(new Grade(grade,studentRepository.getReferenceById(username),courseRepository.getReferenceById(courseId),time));
    }
}
