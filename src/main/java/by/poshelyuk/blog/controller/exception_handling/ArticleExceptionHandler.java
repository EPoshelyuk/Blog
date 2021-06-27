package by.poshelyuk.blog.controller.exception_handling;

import by.poshelyuk.blog.exception.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ArticleExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(UserAlreadyExistsException exception) {
        IncorrectData data = new IncorrectData();
        data.setInfo((exception.getMessage()));
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(AccessDeniedException exception) {
        IncorrectData data = new IncorrectData();
        data.setInfo((exception.getMessage()));
        return new ResponseEntity<>(data, HttpStatus.FORBIDDEN);
    }
}
