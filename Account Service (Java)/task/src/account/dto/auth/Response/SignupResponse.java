package account.dto.auth.Response;

public class SignupResponse {
    private String name;
    private String lastname;
    private String email;

    public SignupResponse(String name, String lastname, String email) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
    }
}
