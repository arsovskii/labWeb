package mk.ukim.finki.wp.model.exceptions;

public class CourseDoesntExistException extends RuntimeException{
    public CourseDoesntExistException() {
        super("Course doesn't exist!");
    }
}
