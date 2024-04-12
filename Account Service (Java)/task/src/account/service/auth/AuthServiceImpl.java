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
        String password = request.getPassword();

        if (isValid(name) || isValid(lastname) || isValid(email) || isValid(password)) {
            throw new IllegalArgumentException("All required fields must be filled!");
        }

        return new SignupResponse(name, lastname, email);
    }

    private Boolean isValid(String field) {
        return field == null || field.isEmpty();
    }
}
