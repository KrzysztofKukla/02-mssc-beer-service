package pl.kukla.krzys.msscbeerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Krzysztof Kukla
 */
@ControllerAdvice
public class MvcExceptionHandler {

    //it will be invoke when ConstraintViolationException will be thrown
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List> handleConstraintException(ConstraintViolationException ex) {
        List<String> errors = new ArrayList<>();
        ex.getConstraintViolations().forEach(i -> {
            errors.add(i.toString());
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
