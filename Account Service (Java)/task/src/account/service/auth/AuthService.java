package account.service.auth;

import account.dto.auth.request.SignupRequest;
import account.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface AuthService {
    User signup(SignupRequest request) throws JsonProcessingException;
}
