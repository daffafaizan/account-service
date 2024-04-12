package account.service.auth;

import account.dto.auth.Request.SignupRequest;
import account.dto.auth.Response.SignupResponse;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public SignupResponse signup(SignupRequest request) {
        String name = request.getName();
        String lastname = request.getLastname();
        String email = request.getEmail();

        if (name == null || lastname == null || email == null) {
            throw new IllegalArgumentException();
        }

        return new SignupResponse(name, lastname, email);
    }
}
