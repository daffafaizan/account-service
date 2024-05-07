package account.dto.auth.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SignupRequest {
    @NotNull()
    @NotBlank()
    private String name;
    @NotNull()
    @NotBlank()
    private String lastname;
    @NotNull()
    @NotBlank()
    @Email(regexp = "^.+@acme.com$")
    @Column(unique = true)
    private String email;
    @NotNull()
    @NotBlank()
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
