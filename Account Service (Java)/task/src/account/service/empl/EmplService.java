package account.service.empl;

import account.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.core.userdetails.UserDetails;

public interface EmplService {
    User getPayment(UserDetails details);
}
