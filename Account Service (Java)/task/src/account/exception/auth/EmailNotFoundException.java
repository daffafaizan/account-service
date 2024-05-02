package account.exception.auth;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailNotFoundException extends RuntimeException {
    public EmailNotFoundException() {
        super("User not found!");
    }
}
