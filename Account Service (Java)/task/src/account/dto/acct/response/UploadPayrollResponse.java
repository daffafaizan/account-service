package account.dto.acct.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UploadPayrollResponse {
    @NotNull
    @NotBlank
    private String status;

    public String getStatus() {
        return this.status;
    }
}
