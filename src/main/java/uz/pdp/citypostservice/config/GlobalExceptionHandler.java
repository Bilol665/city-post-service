package uz.pdp.citypostservice.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import uz.pdp.citypostservice.exceptions.DataNotFoundException;
import uz.pdp.citypostservice.exceptions.NotAcceptableException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {NotAcceptableException.class})
    public ResponseEntity<String> notAcceptable(NotAcceptableException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler(value = {DataNotFoundException.class})
    public ResponseEntity<String> dataNotFound(NotAcceptableException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
