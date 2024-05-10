package account.service.auth;

import account.dto.auth.request.ChangePassRequestDTO;
import account.dto.auth.request.SignupRequestDTO;
import account.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {
    String changepass(ChangePassRequestDTO request, UserDetails userDetails) throws JsonProcessingException;
    User signup(SignupRequestDTO request) throws JsonProcessingException;
}
