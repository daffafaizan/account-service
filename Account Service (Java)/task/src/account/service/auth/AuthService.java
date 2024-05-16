package account.service.auth;

import account.dto.auth.request.ChangePassRequestDTO;
import account.dto.auth.request.SignupRequestDTO;
import account.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {
    String changepass(ChangePassRequestDTO request, UserDetails userDetails);
    User signup(SignupRequestDTO request);
}
