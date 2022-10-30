package mk.ukim.finki.wp.model.exceptions;

public class CourseDoenstExistException extends RuntimeException{
    public CourseDoenstExistException() {
        super("Course doesn't exist!");
    }
}
