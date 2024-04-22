package account.service.auth.impl;

import account.dto.auth.request.SignupRequest;
import account.dto.auth.response.SignupResponse;
import account.model.User;
import account.repository.UserRepository;
import account.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private final UserRepository userRepository;
    private final Pattern emailPattern;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.emailPattern = Pattern.compile("^.+@acme.com$");
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public SignupResponse signup(SignupRequest request) throws Exception {

        if (request.getName().isEmpty() || request.getName() == null || request.getLastname().isEmpty() || request.getLastname() == null || request.getEmail().isEmpty() || request.getEmail() == null || request.getPassword().isEmpty() || request.getPassword() == null || !emailPattern.matcher(request.getEmail()).matches() ) {
            throw new Exception();
        }

        String name = request.getName();
        String lastname = request.getLastname();
        String email = request.getEmail().toLowerCase();
        String password = request.getPassword();

        if (isUserExist(email)) {
            throw new Exception();
        }

        User newUser = new User();
        newUser.setName(name);
        newUser.setLastname(lastname);
        newUser.setEmail(email);
        newUser.setPassword(password);
        userRepository.save(newUser);

        return new SignupResponse(request.getName(), request.getLastname(), request.getEmail());
    }

    private boolean isUserExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
