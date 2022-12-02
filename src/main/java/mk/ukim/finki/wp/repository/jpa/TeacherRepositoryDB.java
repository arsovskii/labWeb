package mk.ukim.finki.wp.repository.jpa;

import mk.ukim.finki.wp.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TeacherRepositoryDB extends JpaRepository<Teacher,Long> {
}
