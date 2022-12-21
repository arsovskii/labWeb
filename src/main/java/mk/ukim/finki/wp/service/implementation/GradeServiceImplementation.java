package mk.ukim.finki.wp.service.implementation;

import mk.ukim.finki.wp.model.Grade;
import mk.ukim.finki.wp.repository.jpa.CourseRepositoryDB;
import mk.ukim.finki.wp.repository.jpa.GradeRepositoryDB;
import mk.ukim.finki.wp.service.GradeService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GradeServiceImplementation implements GradeService {
    private final GradeRepositoryDB gradeRepository;
    private final CourseRepositoryDB courseRepository;

    public GradeServiceImplementation(GradeRepositoryDB gradeRepository, CourseRepositoryDB courseRepository) {
        this.gradeRepository = gradeRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Grade> showGradesBetween(LocalDateTime from, LocalDateTime to) {
        return gradeRepository.findAllByTimestampBetween(from,to);
    }

    @Override
    public List<Grade> showAllGrades(LocalDateTime from, LocalDateTime to, Character first, Character second, Long course) {
        if(first == null){
            first = 'A';
        }
        if(second == null){
            second = 'E';
        }
        if(first>second){
            Character temp = first;
            first = second;
            second = temp;
        }

        if(from == null && to == null){
            if (course == null || course == -1){
                return gradeRepository.getGradesByGradeBetween(first,second);
            }
            return gradeRepository.getGradesByCourseAndGradeBetween(courseRepository.getReferenceById(course),first,second);
        }else if(from == null){
            if (course == null || course == -1){
                return gradeRepository.getGradesByTimestampBetweenAndGradeBetween(LocalDateTime.MIN,to,first,second);
            }
            return gradeRepository.getGradesByCourseAndTimestampBetweenAndGradeBetween(courseRepository.getReferenceById(course),LocalDateTime.MIN,to,first,second);
        }else if(to == null){
            if (course == null || course == -1){
                return gradeRepository.getGradesByTimestampBetweenAndGradeBetween(from,LocalDateTime.now(),first,second);
            }
            return gradeRepository.getGradesByCourseAndTimestampBetweenAndGradeBetween(courseRepository.getReferenceById(course),from,LocalDateTime.now(),first,second);
        }else{
            if (course == null || course == -1){
                return gradeRepository.getGradesByTimestampBetweenAndGradeBetween(from,to,first,second);
            }
            return gradeRepository.getGradesByCourseAndTimestampBetweenAndGradeBetween(courseRepository.getReferenceById(course),from,to,first,second);
        }
    }


    @Override
    public List<Grade> showAllGradesBefore(LocalDateTime to) {
        return gradeRepository.findAllByTimestampBefore(to);
    }
}
