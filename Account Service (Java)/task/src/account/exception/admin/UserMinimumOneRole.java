package account.exception.admin;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserMinimumOneRole extends RuntimeException {
    public UserMinimumOneRole() {
        super("The user must have at least one role!");
    }
}
