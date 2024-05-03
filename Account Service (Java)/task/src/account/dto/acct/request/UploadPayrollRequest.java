package account.dto.acct.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.YearMonth;

public class UploadPayrollRequest {
    @NotNull
    @NotBlank
    private String employee;
    @NotNull
    @NotBlank
    private YearMonth period;
    @NotNull
    @NotBlank
    private BigDecimal salary;

    public String getEmployee() {
        return this.employee;
    }
    public YearMonth getPeriod() {
        return this.period;
    }
    public BigDecimal getSalary() {
        return this.salary;
    }
}
