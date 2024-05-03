package account.service.auth;

import account.dto.auth.request.ChangePassRequest;
import account.dto.auth.request.SignupRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {
    String signup(SignupRequest request) throws JsonProcessingException;
    String changepass(ChangePassRequest request, UserDetails userDetails) throws JsonProcessingException;
}
