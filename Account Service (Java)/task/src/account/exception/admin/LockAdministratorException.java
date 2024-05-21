package account.exception.admin;

public class LockAdministratorException extends RuntimeException {
    public LockAdministratorException() {
        super("Can't lock the ADMINISTRATOR!");
    }
}
