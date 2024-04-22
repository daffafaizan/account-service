package account.service.auth;

import account.dto.auth.request.SignupRequest;
import account.dto.auth.response.SignupResponse;

public interface AuthService {
    SignupResponse signup(SignupRequest request);
}
