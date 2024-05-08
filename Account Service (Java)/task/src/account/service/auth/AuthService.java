package account.service.auth;

import account.dto.auth.request.SignupRequestDTO;
import account.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface AuthService {
    User signup(SignupRequestDTO request) throws JsonProcessingException;
}
