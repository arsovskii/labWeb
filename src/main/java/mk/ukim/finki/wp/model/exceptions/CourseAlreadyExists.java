package mk.ukim.finki.wp.model.exceptions;

public class CourseAlreadyExists extends RuntimeException {
    public CourseAlreadyExists() {
        super("Course already exists!");
    }
}
