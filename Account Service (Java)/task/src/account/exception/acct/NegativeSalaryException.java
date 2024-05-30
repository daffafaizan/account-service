package account.exception.acct;

public class NegativeSalaryException extends RuntimeException {
    public NegativeSalaryException() {
        super("Salary must be non negative!");
    }
}
