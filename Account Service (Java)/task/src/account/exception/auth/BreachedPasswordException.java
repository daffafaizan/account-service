package account.exception.auth;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BreachedPasswordException extends RuntimeException {
    public BreachedPasswordException() {
        super("The password is in the hacker's database!");
    }
}
