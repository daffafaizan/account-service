package account.service.auth;

import account.dto.auth.Request.SignupRequest;
import account.dto.auth.Response.SignupResponse;

public interface AuthService {
    SignupResponse signup(SignupRequest request) throws Exception;
}
