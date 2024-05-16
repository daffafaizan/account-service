package account.exception.auth;

public class RemoveAdministratorException extends RuntimeException {
    public RemoveAdministratorException() {
        super("Can't remove ADMINISTRATOR role!");
    }
}
