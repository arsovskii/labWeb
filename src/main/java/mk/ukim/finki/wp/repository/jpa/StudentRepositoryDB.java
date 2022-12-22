package mk.ukim.finki.wp.repository.jpa;

import mk.ukim.finki.wp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface StudentRepositoryDB extends JpaRepository<Student,String> {
    Student findByUsername(String Username);
    List<Student> findAllByNameOrSurname(String name,String surname);

    Boolean existsByUsername(String username);
}
