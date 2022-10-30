package mk.ukim.finki.wp.model.exceptions;

public class NotEnoughInfoForNewStudentException extends RuntimeException{
    public NotEnoughInfoForNewStudentException() {
        super("Fill all textboxes!");
    }
}
