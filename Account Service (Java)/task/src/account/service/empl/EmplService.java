package account.service.empl;

import account.dto.empl.response.EmplPaymentResponse;
import org.springframework.security.core.userdetails.UserDetails;

public interface EmplService {
    EmplPaymentResponse getPayment(UserDetails details);
}
