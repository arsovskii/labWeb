package mk.ukim.finki.wp.model.exceptions;

public class StudentDoesntExistException extends RuntimeException{
    public StudentDoesntExistException() {
        super("Student with that username doesn't exist");
    }
}
