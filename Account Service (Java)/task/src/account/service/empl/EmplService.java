package account.service.empl;

import account.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface EmplService {
    User getPayment(UserDetails details);
}
