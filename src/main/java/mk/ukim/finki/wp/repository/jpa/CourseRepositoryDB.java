package mk.ukim.finki.wp.repository.jpa;

import mk.ukim.finki.wp.model.Course;
import mk.ukim.finki.wp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CourseRepositoryDB extends JpaRepository<Course,Long> {


}
