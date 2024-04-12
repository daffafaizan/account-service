package account.dto.auth.Response;

public class SignupResponse {
    private final String name;
    private final String lastname;
    private final String email;

    public SignupResponse(String name, String lastname, String email) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
    }

    public String getName() {
        return this.name;
    }
    public String getLastname() {
        return this.lastname;
    }
    public String getEmail() {
        return this.email;
    }
}
