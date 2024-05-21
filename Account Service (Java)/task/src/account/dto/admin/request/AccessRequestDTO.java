package account.dto.admin.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AccessRequestDTO {
    @NotNull
    @NotBlank
    private String user;
    @NotNull
    @NotBlank
    private String operation;

    public AccessRequestDTO(String user, String operation) {
        this.user = user;
        this.operation = operation;
    }

    public String getUser() {
        return this.user;
    }
    public String getOperation() {
        return this.operation;
    }
}
