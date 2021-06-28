package by.poshelyuk.blog.exception;

public class UserAlreadyExistsException extends Exception {

    public UserAlreadyExistsException(String email) {
        super(String.format("User already exists with email" + email));
    }
}
