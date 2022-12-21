package mk.ukim.finki.wp.repository.jpa;

import mk.ukim.finki.wp.model.Course;
import mk.ukim.finki.wp.model.Grade;
import mk.ukim.finki.wp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface GradeRepositoryDB extends JpaRepository<Grade,Long> {
    List<Grade> getGradesByCourse(Course course);
    List<Grade> findAllByTimestampBetween(LocalDateTime from, LocalDateTime to);
    List<Grade> findAllByTimestampBefore(LocalDateTime to);
    List<Grade> getGradesByCourseAndTimestampBetweenAndGradeBetween(Course course, LocalDateTime timestamp, LocalDateTime timestamp2, Character grade, Character grade2);
    List<Grade> getGradesByCourseAndGradeBetween(Course course, Character grade, Character grade2);
    List<Grade> getGradesByGradeBetween(Character grade, Character grade2);
    List<Grade> getGradesByTimestampBetweenAndGradeBetween(LocalDateTime timestamp, LocalDateTime timestamp2, Character grade, Character grade2);
}
