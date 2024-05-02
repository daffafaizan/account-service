package account.dto.auth.request;

import jakarta.validation.constraints.NotNull;

public class SignupRequest {
    @NotNull(message = "Name field is required!")
    private String name;
    @NotNull(message = "Last name field is required!")
    private String lastname;
    @NotNull(message = "Email field is required!")
    private String email;
    @NotNull(message = "Password field is required!")
    private String password;

    public String getName() {
        return this.name;
    }
    public String getLastname() {
        return this.lastname;
    }
    public String getEmail() {
        return this.email;
    }
    public String getPassword() {
        return this.password;
    }
}
