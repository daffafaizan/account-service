package account.exception.acct;

public class PeriodIsInvalidException extends RuntimeException {
    public PeriodIsInvalidException() {
        super("Wrong date!");
    }
}
