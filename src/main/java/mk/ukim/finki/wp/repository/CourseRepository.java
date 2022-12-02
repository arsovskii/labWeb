package mk.ukim.finki.wp.repository;

import mk.ukim.finki.wp.bootstrap.DataHolder;
import mk.ukim.finki.wp.model.Course;
import mk.ukim.finki.wp.model.Student;
import mk.ukim.finki.wp.model.Teacher;
import mk.ukim.finki.wp.model.exceptions.CourseAlreadyExists;
import mk.ukim.finki.wp.model.exceptions.CourseDoesntExistException;
import mk.ukim.finki.wp.model.exceptions.StudentAlreadyAttendingCourseException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class CourseRepository {

    public List<Course> findAllCourses() {
        return DataHolder.courses;
    }


    public Optional<Course> findById(Long courseId) {
        return DataHolder.courses.stream().filter(i -> i.getCourseId().equals(courseId)).findFirst();
    }

    public Set<Student> findAllStudentsByCourse(Long courseId) {
        try {
            if (findById(courseId).isEmpty()) {
                throw new CourseDoesntExistException();
            }
            return findById(courseId).get().getStudents();
        } catch (Exception e) {
            return null;
        }

    }

    public Course addStudentToCourse(Student student, Course course) throws CourseDoesntExistException, StudentAlreadyAttendingCourseException {

        if (findById(course.getCourseId()).isEmpty()) {
            throw new CourseDoesntExistException();
        }

        if (course.getStudents().stream().anyMatch(i -> i.getUsername().equals(student.getUsername()))) {
            throw new StudentAlreadyAttendingCourseException();
        }
        findById(course.getCourseId()).get().getStudents().add(student);

        return course;
    }

    public Course addCourse(Course course) throws CourseAlreadyExists{
        System.out.println(course);
        System.out.println(findAllCourses());
        if (DataHolder.courses.stream().anyMatch(i->i.getName().equals(course.getName()))){
            System.out.println("POSTOJ!!!!????");
            throw new CourseAlreadyExists();
        }
        DataHolder.courses.add(course);
        return course;
    }
    public void deleteCourseById(Long id){
        try{
            DataHolder.courses.remove(this.findById(id).orElseThrow(CourseDoesntExistException::new));

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public Course editCourse(Long id, String name, String description, Teacher teacher) throws CourseAlreadyExists{
        if (DataHolder.courses.stream().anyMatch(i->i.getName().equals(name))){
            throw new CourseAlreadyExists();
        }
        Course course = DataHolder.courses.stream().filter(i->i.getCourseId().equals(id)).findFirst().orElseThrow(CourseDoesntExistException::new);
        course.setName(name);
        course.setDescription(description);
        course.setTeacher(teacher);

        return course;
    }



}
