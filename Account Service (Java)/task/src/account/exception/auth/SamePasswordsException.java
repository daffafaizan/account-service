package account.exception.auth;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SamePasswordsException extends RuntimeException {
    public SamePasswordsException() {
        super("The passwords must be different!");
    }
}
