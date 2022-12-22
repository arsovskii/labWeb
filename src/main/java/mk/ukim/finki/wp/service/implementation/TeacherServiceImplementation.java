package mk.ukim.finki.wp.service.implementation;

import mk.ukim.finki.wp.model.Teacher;
import mk.ukim.finki.wp.repository.jpa.TeacherRepositoryDB;
import mk.ukim.finki.wp.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImplementation implements TeacherService {

    private final TeacherRepositoryDB teacherRepository;

    public TeacherServiceImplementation(TeacherRepositoryDB teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher save(String name, String surname) {
        return teacherRepository.save(new Teacher(name,surname));
    }
}
