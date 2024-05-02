package account.service.auth.impl;

import account.dto.auth.request.SignupRequest;
import account.dto.auth.response.SignupResponse;
import account.exception.auth.EmailAlreadyExistsException;
import account.exception.auth.EmailNotFoundException;
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
    private final PasswordEncoder passwordEncoder;
    private final Pattern emailPattern;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailPattern = Pattern.compile("^.+@acme.com$");
    }

    @Override
    public SignupResponse signup(SignupRequest request) {

        String name = request.getName();
        String lastname = request.getLastname();
        String email = request.getEmail().toLowerCase();
        String password = request.getPassword();

        if (isUserExist(email)) {
            throw new EmailAlreadyExistsException();
        }

        User newUser = new User();
        newUser.setName(name);
        newUser.setLastname(lastname);
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password));

        userRepository.save(newUser);

        User user = userRepository.findByEmail(email)
                .orElseThrow(EmailNotFoundException::new);

        return new SignupResponse(user.getId(), name, lastname, email);
    }

    private boolean isUserExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
