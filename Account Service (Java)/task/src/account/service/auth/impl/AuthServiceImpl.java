package account.service.auth.impl;

import account.dto.auth.request.SignupRequest;
import account.dto.auth.response.SignupResponse;
import account.service.auth.AuthService;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class AuthServiceImpl implements AuthService {
    Pattern emailPattern = Pattern.compile("^.+@acme.com$");

    @Override
    public SignupResponse signup(SignupRequest request) throws Exception {

        if (request.getName().isEmpty() || request.getName() == null || request.getLastname().isEmpty() || request.getLastname() == null || request.getEmail().isEmpty() || request.getEmail() == null || request.getPassword().isEmpty() || request.getPassword() == null || !emailPattern.matcher(request.getEmail()).matches() ) {
            throw new Exception();
        }

        return new SignupResponse(request.getName(), request.getLastname(), request.getEmail());
    }
}
