package account.exception.admin;

public class RemoveAdministratorException extends RuntimeException {
    public RemoveAdministratorException() {
        super("Can't remove ADMINISTRATOR role!");
    }
}
