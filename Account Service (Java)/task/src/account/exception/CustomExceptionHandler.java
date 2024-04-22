package account.exception;

import account.exception.auth.EmailAlreadyExistsException;
import account.exception.auth.EmailNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> emailAlreadyExistsExceptionHandler(Exception e) {
        ErrorResponse errorResponse = ErrorResponse.builder(e, HttpStatus.BAD_REQUEST, e.getMessage()).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = EmailNotFoundException.class)
    public ResponseEntity<ErrorResponse> emailNotFoundExceptionHandler(Exception e) {
        ErrorResponse errorResponse = ErrorResponse.builder(e, HttpStatus.BAD_REQUEST, e.getMessage()).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
