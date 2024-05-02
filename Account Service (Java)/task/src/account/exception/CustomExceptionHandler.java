package account.exception;

import account.exception.auth.EmailAlreadyExistsException;
import account.exception.auth.EmailNotFoundException;
import account.exception.auth.InvalidMethodArgumentsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = EmailAlreadyExistsException.class)
    public ResponseStatusException emailAlreadyExistsExceptionHandler(Exception e) {
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(value = EmailNotFoundException.class)
    public ResponseStatusException emailNotFoundExceptionHandler(Exception e) {
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(value = InvalidMethodArgumentsException.class)
    public ResponseEntity<Object> invalidMethodArgumentsExceptionHandler(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
