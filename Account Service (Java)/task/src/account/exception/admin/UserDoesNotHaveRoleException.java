package account.exception.admin;

public class UserDoesNotHaveRoleException extends RuntimeException {
    public UserDoesNotHaveRoleException() {
        super("The user does not have a role!");
    }
}
