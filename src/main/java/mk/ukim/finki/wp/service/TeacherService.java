package mk.ukim.finki.wp.service;

import mk.ukim.finki.wp.model.Teacher;

import java.util.List;

public interface TeacherService {
    public List<Teacher> findAll();
    public Teacher save(String name, String surname);
}
