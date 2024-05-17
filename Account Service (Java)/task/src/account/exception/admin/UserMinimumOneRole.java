package account.exception.admin;

public class UserMinimumOneRole extends RuntimeException {
    public UserMinimumOneRole() {
        super("The user must have at least one role!");
    }
}
