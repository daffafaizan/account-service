package account.service.auth;

import account.dto.auth.request.SignupRequest;
import account.dto.auth.response.SignupResponse;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public SignupResponse signup(SignupRequest request) throws Exception {
        String name = request.getName();
        String lastname = request.getLastname();
        String email = request.getEmail();
        String password = request.getPassword();

        if (isInvalid(name) || isInvalid(lastname) || isInvalid(email) || isInvalid(password) || !email.endsWith("@acme.com")) {
            throw new Exception();
        }

        return new SignupResponse(name, lastname, email);
    }

    private Boolean isInvalid(String field) {
        return field == null || field.isEmpty();
    }
}
