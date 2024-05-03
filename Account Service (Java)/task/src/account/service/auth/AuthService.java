package account.service.auth;

import account.dto.auth.request.ChangePassRequest;
import account.dto.auth.request.SignupRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface AuthService {
    String signup(SignupRequest request) throws JsonProcessingException;
    String changepass(ChangePassRequest request) throws JsonProcessingException;
}
