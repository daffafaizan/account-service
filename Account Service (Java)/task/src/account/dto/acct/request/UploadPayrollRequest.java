package account.dto.acct.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.YearMonth;

public class UploadPayrollRequest {
    @NotNull
    @NotBlank
    private String employee;
    @NotNull
    @NotBlank
    @DateTimeFormat(pattern = "mm-YYYY")
    @Column(unique = true)
    private YearMonth period;
    @NotNull
    @NotBlank
    @Min(value = 0)
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
