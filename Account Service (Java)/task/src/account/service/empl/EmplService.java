package account.service.empl;

import account.model.Payroll;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface EmplService {
    List<Payroll> getPayment(UserDetails details);
}
