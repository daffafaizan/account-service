package account.service.empl;

import account.dto.empl.response.PaymentResponseDTO;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface EmplService {
    List<PaymentResponseDTO> getPayment(UserDetails details);
    PaymentResponseDTO getPaymentByPeriod(UserDetails details, String period);
}
