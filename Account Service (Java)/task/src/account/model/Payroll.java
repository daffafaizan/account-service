package account.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.YearMonth;

@Entity
@Table(name = "payroll")
public class Payroll {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Integer payrollId;
    @NotNull
    @NotBlank
    @JsonIgnore
    private String employee;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String lastname;
    @NotNull
    @DateTimeFormat(pattern = "mm-YYYY")
    private YearMonth period;
    @NotNull
    @Min(value = 0)
    private BigDecimal salary;

    public Integer getPayrollId() {
        return this.payrollId;
    }
    public void setPayrollId(Integer payrollId) {
        this.payrollId = payrollId;
    }
    public String getEmployee() {
        return this.employee;
    }
    public void setEmployee(String employee) {
        this.employee = employee;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastname() {
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
    public String getSalary() {
        return centstoDollars(this.salary);
    }
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
    private String centstoDollars(BigDecimal salary) {
        BigDecimal divisor = new BigDecimal("100");
        String dollars = salary.divide(divisor, RoundingMode.FLOOR).toBigInteger().toString();
        String cents = salary.remainder(divisor).toBigInteger().toString();

        return dollars + " dollar(s) " + cents + " cent(s)";
    }
}
