package account.dto.acct.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;

public class PayrollRequestDTO {
    @NotNull
    @NotBlank
    private String employee;
    @NotNull
    @NotBlank
    @DateTimeFormat(pattern = "mm-YYYY")
    private String period;
    @NotNull
    @Min(value = 0)
    private BigDecimal salary;

    public String getEmployee() {
        return this.employee;
    }
    public String getPeriod() {
        return this.period;
    }
    public BigDecimal getSalary() {
        return this.salary;
    }
}
