package mk.ukim.finki.wp.model.exceptions;

public class UsernameAlreadyInUse extends RuntimeException{
    public UsernameAlreadyInUse(String message) {
        super("Username already in use: " + message);
    }
}
