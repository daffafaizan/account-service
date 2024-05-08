package account.service.auth.impl;

import account.adapter.UserAdapter;
import account.dto.auth.request.ChangePassRequestDTO;
import account.exception.auth.*;
import account.dto.auth.request.SignupRequestDTO;
import account.exception.auth.EmailAlreadyExistsException;
import account.exception.auth.EmailNotFoundException;
import account.model.User;
import account.repository.UserRepository;
import account.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User signup(SignupRequestDTO request) {

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

        return userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(EmailNotFoundException::new);
    }

    @Override
    public String changepass(ChangePassRequestDTO request, UserDetails userDetails) {
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

        return user.getEmail();
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
