package mk.ukim.finki.wp.service.implementation;

import mk.ukim.finki.wp.model.Grade;
import mk.ukim.finki.wp.repository.jpa.GradeRepositoryDB;
import mk.ukim.finki.wp.service.GradeService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GradeServiceImplementation implements GradeService {
    private final GradeRepositoryDB gradeRepository;

    public GradeServiceImplementation(GradeRepositoryDB gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @Override
    public List<Grade> showGradesBetween(LocalDateTime from, LocalDateTime to) {
        return gradeRepository.findAllByTimestampBetween(from,to);
    }

    @Override
    public List<Grade> showAllGrades() {
        return gradeRepository.findAll();
    }

    @Override
    public List<Grade> showAllGradesBefore(LocalDateTime to) {
        return gradeRepository.findAllByTimestampBefore(to);
    }
}
