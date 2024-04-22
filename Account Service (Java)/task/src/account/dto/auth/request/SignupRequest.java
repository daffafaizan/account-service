package account.dto.auth.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SignupRequest {
    @NotNull(message = "Name field is required!")
    @NotBlank(message = "Name field can not be blank!")
    private String name;
    @NotNull(message = "Last name field is required!")
    @NotBlank(message = "Last name field can not be blank!")
    private String lastname;
    @NotNull(message = "Email field is required!")
    @NotBlank(message = "Email field can not be blank!")
    @Email(message = "Email should be valid!", regexp = "^.+@acme.com$")
    private String email;
    @NotNull(message = "Password field is required!")
    @NotBlank(message = "Password field can not be blank!")
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
