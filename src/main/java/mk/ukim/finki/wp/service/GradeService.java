package mk.ukim.finki.wp.service;

import mk.ukim.finki.wp.model.Grade;

import java.time.LocalDateTime;
import java.util.List;

public interface GradeService {
    List<Grade> showGradesBetween(LocalDateTime from, LocalDateTime to);
    List<Grade> showAllGrades(LocalDateTime from, LocalDateTime to, Character first, Character second, Long course);
    List<Grade> showAllGradesBefore(LocalDateTime to);
}
