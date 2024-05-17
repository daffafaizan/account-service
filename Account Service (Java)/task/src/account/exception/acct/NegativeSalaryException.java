package account.exception.acct;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NegativeSalaryException extends RuntimeException {
    public NegativeSalaryException() {
        super("Salary must be non negative!");
    }
}
