package account.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Object> runtimeExceptionHandler(Exception exception, HttpServletRequest request) {
        return new ResponseEntity<>(customExceptionResponse(HttpStatus.BAD_REQUEST, request, exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> methodArgumentNotValidExceptionHandler(Exception exception, HttpServletRequest request) {
        return new ResponseEntity<>(customExceptionResponse(HttpStatus.BAD_REQUEST, request, null), HttpStatus.BAD_REQUEST);
    }

}
