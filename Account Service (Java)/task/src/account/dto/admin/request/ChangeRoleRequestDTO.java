package account.dto.admin.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ChangeRoleRequestDTO {
    @NotNull
    @NotBlank
    private String user;
    @NotNull
    @NotBlank
    private String role;
    @NotNull
    @NotBlank
    private String operation;

    public String getUser() {
        return this.user;
    }
    public String getRole() {
        return this.role;
    }
    public String getOperation() {
        return this.operation;
    }
}
