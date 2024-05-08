package account.service.auth.impl;

import account.dto.auth.request.SignupRequest;
import account.exception.auth.EmailAlreadyExistsException;
import account.exception.auth.EmailNotFoundException;
import account.model.User;
import account.repository.UserRepository;
import account.service.auth.AuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User signup(SignupRequest request) throws JsonProcessingException {

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

        return userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(EmailNotFoundException::new);
    }

    private boolean isUserExist(String email) {
        return userRepository.findByEmailIgnoreCase(email).isPresent();
    }
}
