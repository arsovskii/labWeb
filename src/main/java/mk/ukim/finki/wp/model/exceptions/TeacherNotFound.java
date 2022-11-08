package mk.ukim.finki.wp.model.exceptions;

public class TeacherNotFound extends RuntimeException{
    public TeacherNotFound() {
        super("That teacher doesn't exist!");
    }
}
