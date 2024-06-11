package account.exception.acct;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PeriodIsInvalidException extends RuntimeException {
    public PeriodIsInvalidException() {
        super("Wrong date!");
    }
}
