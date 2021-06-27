package by.poshelyuk.blog.controller.exception_handling;

import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DataBaseExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(ConstraintViolationException exception) {
        IncorrectData data = new IncorrectData();
        data.setInfo((exception.getMessage()));
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(DataException exception) {
        IncorrectData data = new IncorrectData();
        data.setInfo((exception.getMessage()));
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
