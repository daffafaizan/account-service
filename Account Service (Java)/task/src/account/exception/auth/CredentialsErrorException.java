package account.exception.auth;

public class CredentialsErrorException extends RuntimeException {
    public CredentialsErrorException() {
        super("Credentials error!");
    }
}
