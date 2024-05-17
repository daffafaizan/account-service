package account.exception.admin;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidRoleCombination extends RuntimeException {
    public InvalidRoleCombination() {
        super("The user cannot combine administrative and business roles!");
    }
}
