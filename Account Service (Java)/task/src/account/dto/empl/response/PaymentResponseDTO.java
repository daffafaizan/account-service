package account.dto.empl.response;

import java.math.BigDecimal;

public class PaymentResponseDTO {
    private String name;
    private String lastname;
    private String period;
    private String salary;

    public PaymentResponseDTO(String name, String lastname, String period, String salary) {
        this.name = name;
        this.lastname = lastname;
        this.period = period;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPeriod() {
        return period;
    }

    public String getSalary() {
        return salary;
    }
}
