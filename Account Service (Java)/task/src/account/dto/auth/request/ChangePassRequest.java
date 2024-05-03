package account.dto.auth.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ChangePassRequest {
    @NotNull
    @NotBlank
    private String new_password;

    public String getNew_password() {
        return this.new_password;
    }
}
