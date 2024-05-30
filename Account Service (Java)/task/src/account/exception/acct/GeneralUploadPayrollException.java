package account.exception.acct;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GeneralUploadPayrollException extends RuntimeException {
    public GeneralUploadPayrollException() {
        super("Error!");
    }
}
