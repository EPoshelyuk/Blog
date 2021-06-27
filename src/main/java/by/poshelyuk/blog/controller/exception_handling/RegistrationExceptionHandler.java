package by.poshelyuk.blog.controller.exception_handling;

import by.poshelyuk.blog.exception.JwtAuthenticationException;
import by.poshelyuk.blog.exception.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.rmi.activation.ActivationException;

@ControllerAdvice
public class RegistrationExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(ActivationException exception) {
        IncorrectData data = new IncorrectData();
        data.setInfo((exception.getMessage()));
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(JwtAuthenticationException exception) {
        IncorrectData data = new IncorrectData();
        data.setInfo((exception.getMessage()));
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(UserAlreadyExistsException exception) {
        IncorrectData data = new IncorrectData();
        data.setInfo((exception.getMessage()));
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
