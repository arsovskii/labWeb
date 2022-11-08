package mk.ukim.finki.wp.service.implementation;

import mk.ukim.finki.wp.model.Teacher;
import mk.ukim.finki.wp.repository.TeacherRepository;
import mk.ukim.finki.wp.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImplementation implements TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherServiceImplementation(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }
}