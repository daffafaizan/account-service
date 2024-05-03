package account.service.auth.impl;

import account.adapter.UserAdapter;
import account.dto.auth.request.ChangePassRequest;
import account.dto.auth.request.SignupRequest;
import account.dto.auth.response.ChangePassResponse;
import account.dto.auth.response.SignupResponse;
import account.exception.auth.*;
import account.model.User;
import account.repository.UserRepository;
import account.service.auth.AuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public String signup(SignupRequest request) throws JsonProcessingException {

        String name = request.getName();
        String lastname = request.getLastname();
        String email = request.getEmail().toLowerCase();
        String password = request.getPassword();

        if (isUserExist(email)) {
            throw new EmailAlreadyExistsException();
        }
        if (isNewPasswordInHackersDatabase(password)) {
            throw new BreachedPasswordException();
        }

        User newUser = new User();
        newUser.setName(name);
        newUser.setLastname(lastname);
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password));

        userRepository.save(newUser);
        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(EmailNotFoundException::new);

        SignupResponse response = new SignupResponse(user.getId(), name, lastname, email);

        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
    }

    @Override
    public String changepass(ChangePassRequest request, UserDetails userDetails) throws JsonProcessingException {
        String newPassword = request.getNew_password();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            throw new CredentialsErrorException();
        }
        UserAdapter userAdapter = (UserAdapter) auth.getPrincipal();
        String email = userAdapter.getUsername();

        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(EmailNotFoundException::new);

        if (isOldAndNewPasswordMatches(newPassword, user.getPassword())) {
            throw new SamePasswordsException();
        }
        if (newPassword.length() < 12) {
            throw new MinimumCharactersException();
        }
        if (isNewPasswordInHackersDatabase(newPassword)) {
            throw new BreachedPasswordException();
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        ChangePassResponse response = new ChangePassResponse(user.getEmail(), "The password has been updated successfully");
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
    }

    private boolean isUserExist(String email) {
        return userRepository.findByEmailIgnoreCase(email).isPresent();
    }

    private boolean isOldAndNewPasswordMatches(String newPassword, String oldPassword) {
        return passwordEncoder.matches(newPassword, oldPassword);
    }

    private boolean isNewPasswordInHackersDatabase(String newPassword) {
        String[] database = new String[]{"PasswordForJanuary", "PasswordForFebruary", "PasswordForMarch", "PasswordForApril",
                "PasswordForMay", "PasswordForJune", "PasswordForJuly", "PasswordForAugust",
                "PasswordForSeptember", "PasswordForOctober", "PasswordForNovember", "PasswordForDecember"};

        return Arrays.asList(database).contains(newPassword);
    }
}
