package account.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.YearMonth;

@Entity
@Table(name = "payroll")
public class Payroll {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer payrollId;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String lastname;
    @NotNull
    @NotBlank
    @DateTimeFormat(pattern = "mm-YYYY")
    @Column(unique = true)
    private YearMonth period;
    @NotNull
    @NotBlank
    @Min(value = 0)
    private BigDecimal salary;

    public Integer getPayrollId() {
        return this.payrollId;
    }
    public void setPayrollId(Integer payrollId) {
        this.payrollId = payrollId;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String lastname() {
        return this.lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public YearMonth getPeriod() {
        return this.period;
    }
    public void setPeriod(YearMonth period) {
        this.period = period;
    }
    public BigDecimal getSalary() {
        return this.salary;
    }
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}
