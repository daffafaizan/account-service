package account.dto.auth.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ChangePassResponse {
    @NotNull
    @NotBlank
    private String email;
    @NotNull
    @NotBlank
    private String status;

    public ChangePassResponse(String email, String status) {
        this.email = email;
        this.status = status;
    }

    public String getEmail() {
        return this.email;
    }
    public String getStatus() {
        return this.status;
    }
}
