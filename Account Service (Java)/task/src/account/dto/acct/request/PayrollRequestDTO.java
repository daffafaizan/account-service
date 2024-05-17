package account.dto.acct.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;

public class PayrollRequestDTO {
    private String employee;
    private String period;
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
