package mk.ukim.finki.wp.repository;

import mk.ukim.finki.wp.bootstrap.DataHolder;
import mk.ukim.finki.wp.model.Student;
import mk.ukim.finki.wp.model.exceptions.StudentDoesntExistException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {


    public List<Student> findAllStudents() {
        return DataHolder.students;
    }

    public List<Student> findAllByNameOrSurname(String text) {
        return DataHolder.students.stream().filter(i -> i.getName().equals(text) || i.getSurname().equals(text)).toList();
    }

    public Student findByUsername(String username) {
        try{
            if (DataHolder.students.stream().filter(i -> i.getUsername().equals(username)).findFirst().isEmpty()) {
                throw new StudentDoesntExistException();
            }
            return DataHolder.students.stream().filter(i -> i.getUsername().equals(username)).findFirst().get();

        }catch (Exception e){
            return null;
        }
    }

    public Student addStudent(Student student) {
        DataHolder.students.add(student);
        return student;
    }

}
