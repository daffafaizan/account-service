package account.service.auth.impl;

import account.dto.auth.request.SignupRequest;
import account.dto.auth.response.SignupResponse;
import account.service.auth.AuthService;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class AuthServiceImpl implements AuthService {
    Pattern emailPattern = Pattern.compile("^.+@acme.com$");

    @Override
    public SignupResponse signup(SignupRequest request) throws Exception {

        if (StringUtils.isEmpty(request.getName()) || StringUtils.isEmpty(request.getLastname()) || StringUtils.isEmpty(request.getEmail()) || StringUtils.isEmpty(request.getPassword()) || !emailPattern.matcher(request.getEmail()).matches() ) {
            throw new Exception();
        }

        return new SignupResponse(request.getName(), request.getLastname(), request.getEmail());
    }
}
