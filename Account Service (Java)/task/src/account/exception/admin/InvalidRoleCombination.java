package account.exception.admin;

public class InvalidRoleCombination extends RuntimeException {
    public InvalidRoleCombination() {
        super("The user cannot combine administrative and business roles!");
    }
}
