package account.exception;

import account.exception.acct.GeneralUploadPayrollException;
import account.exception.acct.NegativeSalaryException;
import account.exception.acct.PeriodIsInvalidException;
import account.exception.auth.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler {

    public Map<String, Object> customExceptionResponse(HttpStatus status, HttpServletRequest request, String message) {
        Map<String, Object> response = new LinkedHashMap<>();

        LocalTime time = LocalTime.now();
        Integer code = status.value();
        String error = status.getReasonPhrase();
        String path = request.getRequestURI();

        response.put("timestamp", time);
        response.put("status", code);
        response.put("error", error);
        if (message != null) {
            response.put("message", message);
        }
        response.put("path", path);

        return response;
    }

    // Authentication
    @ExceptionHandler(value = EmailAlreadyExistsException.class)
    public ResponseEntity<Object> emailAlreadyExistsExceptionHandler(Exception exception, HttpServletRequest request) {
        return new ResponseEntity<>(customExceptionResponse(HttpStatus.BAD_REQUEST, request, exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = EmailNotFoundException.class)
    public ResponseEntity<Object> emailNotFoundExceptionHandler(Exception exception, HttpServletRequest request) {
        return new ResponseEntity<>(customExceptionResponse(HttpStatus.BAD_REQUEST, request, exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> methodArgumentNotValidExceptionHandler(Exception exception, HttpServletRequest request) {
        return new ResponseEntity<>(customExceptionResponse(HttpStatus.BAD_REQUEST, request, null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<Object> HttpMessageNotReadableExceptionHandler(Exception exception, HttpServletRequest request) {
        return new ResponseEntity<>(customExceptionResponse(HttpStatus.BAD_REQUEST, request, null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BreachedPasswordException.class)
    public ResponseEntity<Object> breachedPasswordExceptionHandler(Exception exception, HttpServletRequest request) {
        return new ResponseEntity<>(customExceptionResponse(HttpStatus.BAD_REQUEST, request, exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MinimumCharactersException.class)
    public ResponseEntity<Object> minimumCharactersExceptionHandler(Exception exception, HttpServletRequest request) {
        return new ResponseEntity<>(customExceptionResponse(HttpStatus.BAD_REQUEST, request, exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = SamePasswordsException.class)
    public ResponseEntity<Object> samePasswordsExceptionHandler(Exception exception, HttpServletRequest request) {
        return new ResponseEntity<>(customExceptionResponse(HttpStatus.BAD_REQUEST, request, exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = CredentialsErrorException.class)
    public ResponseEntity<Object> credentialsErrorExceptionHandler(Exception exception, HttpServletRequest request) {
        return new ResponseEntity<>(customExceptionResponse(HttpStatus.BAD_REQUEST, request, exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    // Accountant
    @ExceptionHandler(value = GeneralUploadPayrollException.class)
    public ResponseEntity<Object> generalUploadPayrollExceptionHandler(Exception exception, HttpServletRequest request) {
        return new ResponseEntity<>(customExceptionResponse(HttpStatus.BAD_REQUEST, request, exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NegativeSalaryException.class)
    public ResponseEntity<Object> negativeSalaryExceptionHandler(Exception exception, HttpServletRequest request) {
        return new ResponseEntity<>(customExceptionResponse(HttpStatus.BAD_REQUEST, request, exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = PeriodIsInvalidException.class)
    public ResponseEntity<Object> periodIsInvalidExceptionHandler(Exception exception, HttpServletRequest request) {
        return new ResponseEntity<>(customExceptionResponse(HttpStatus.BAD_REQUEST, request, exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
