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
    private Long id;
    @NotNull
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "users_id")
    private User employee;
    @NotNull
    @DateTimeFormat(pattern = "mm-YYYY")
    private YearMonth period;
    @NotNull
    @Min(value = 0)
    private BigDecimal salary;

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public User getEmployee() {
        return this.employee;
    }
    public void setEmployee(User employee) {
        this.employee = employee;
    }
    public String getPeriod() {
        return yearMonthToString(this.period);
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
    private String yearMonthToString(YearMonth yearMonth) {
        String monthTemp = String.valueOf(yearMonth.getMonth());
        String month = monthTemp.charAt(0) + monthTemp.substring(1).toLowerCase();
        String year = String.valueOf(yearMonth.getYear());

        return month + "-" + year;
    }
}
