package account.service.empl;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.core.userdetails.UserDetails;

public interface EmplService {
    String getPayment(UserDetails details) throws JsonProcessingException;
}
