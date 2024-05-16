package account.exception.auth;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MinimumCharactersException extends RuntimeException {
    public MinimumCharactersException() {
        super("Password length must be 12 chars minimum!");
    }
}
