package mk.ukim.finki.wp.model.exceptions;

public class StudentAlreadyAttendingCourseException extends RuntimeException{
    public StudentAlreadyAttendingCourseException() {
        super("Student is already attending the course!");
    }
}
