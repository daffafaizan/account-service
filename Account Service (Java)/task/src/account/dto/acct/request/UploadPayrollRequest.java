package account.dto.acct.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UploadPayrollRequest {
    @NotNull
    @NotBlank
    private String employee;
    @NotNull
    @NotBlank
    private String period;
    @NotNull
    @NotBlank
    private Integer salary;

    public String getEmployee() {
        return this.employee;
    }
    public String getPeriod() {
        return this.period;
    }
    public Integer getSalary() {
        return this.salary;
    }
}
