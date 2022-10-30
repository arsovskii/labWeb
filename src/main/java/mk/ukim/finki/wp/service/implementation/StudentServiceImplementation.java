package mk.ukim.finki.wp.service.implementation;

import mk.ukim.finki.wp.model.Student;
import mk.ukim.finki.wp.model.exceptions.NotEnoughInfoForNewStudentException;
import mk.ukim.finki.wp.repository.StudentRepository;
import mk.ukim.finki.wp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class StudentServiceImplementation implements StudentService {


    private StudentRepository studentRepository;

    public StudentServiceImplementation(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> listAll() {
        return studentRepository.findAllStudents();
    }

    @Override
    public List<Student> searchByNameOrSurname(String text) {
        return studentRepository.findAllByNameOrSurname(text);
    }

    @Override
    public Student save(String username, String password, String name, String surname) throws NotEnoughInfoForNewStudentException{

        if (Objects.equals(username, "") || Objects.equals(name, "") || Objects.equals(surname, "") || Objects.equals(password, "")) {
            throw new NotEnoughInfoForNewStudentException();
        }
        return studentRepository.addStudent(new Student(username, password, name, surname));


    }
}
