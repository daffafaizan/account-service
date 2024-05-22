package account.service.auth.impl;

import account.adapter.UserAdapter;
import account.dto.auth.request.ChangePassRequestDTO;
import account.exception.auth.*;
import account.dto.auth.request.SignupRequestDTO;
import account.exception.auth.EmailAlreadyExistsException;
import account.exception.auth.EmailNotFoundException;
import account.model.Event;
import account.model.Group;
import account.model.User;
import account.repository.GroupRepository;
import account.repository.UserRepository;
import account.service.auth.AuthService;
import account.service.log.LogService;
import jakarta.servlet.http.HttpServletRequest;
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
    private final GroupRepository groupRepository;
    private final PasswordEncoder passwordEncoder;
    private final LogService logService;
    private final Authentication auth;
    private final HttpServletRequest request;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, GroupRepository groupRepository, PasswordEncoder passwordEncoder, LogService logService, HttpServletRequest request) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.passwordEncoder = passwordEncoder;
        this.logService = logService;
        this.auth = SecurityContextHolder.getContext().getAuthentication();
        this.request = request;
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

        if (userRepository.findAll().isEmpty()) {
            updateRole(newUser, "ADMINISTRATOR");
        } else {
            updateRole(newUser, "USER");
        }

        userRepository.save(newUser);
        logService.createLog(
                Event.CREATE_USER.name(),
                "Anonymous",
                email,
                this.request.getRequestURI()
        );

        return userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(EmailNotFoundException::new);
    }

    @Override
    public String changepass(ChangePassRequestDTO request, UserDetails userDetails) {
        String newPassword = request.getNew_password();

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
        logService.createLog(
                Event.CHANGE_PASSWORD.name(),
                auth.getName(),
                email,
                this.request.getRequestURI()
        );

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

    private void updateRole(User user, String role) {
        switch (role) {
            case "ADMINISTRATOR" -> {
                Group group = groupRepository.findByRole("ROLE_ADMINISTRATOR");
                user.addUserGroups(group);
            }
            case "ACCOUNTANT" -> {
                Group group = groupRepository.findByRole("ROLE_ACCOUNTANT");
                user.addUserGroups(group);
            }
            case "USER" -> {
                Group group = groupRepository.findByRole("ROLE_USER");
                user.addUserGroups(group);
            }
        }
    }
}
