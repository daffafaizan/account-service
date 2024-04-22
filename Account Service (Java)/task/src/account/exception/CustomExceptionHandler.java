package account.exception;

import account.exception.auth.EmailAlreadyExistsException;
import account.exception.auth.EmailNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = EmailAlreadyExistsException.class)
    public ResponseEntity<Object> emailAlreadyExistsExceptionHandler(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = EmailNotFoundException.class)
    public ResponseEntity<Object> emailNotFoundExceptionHandler(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
