package account.config;

import account.adapter.UserAdapter;
import account.exception.auth.EmailNotFoundException;
import account.model.Event;
import account.model.Group;
import account.model.User;
import account.repository.UserRepository;
import account.service.log.LogService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class AuthenticationEvents {

    private final UserRepository userRepository;
    private final LogService logService;
    private final HttpServletRequest request;

    @Autowired
    public AuthenticationEvents(UserRepository userRepository, LogService logService, HttpServletRequest request) {
        this.userRepository = userRepository;
        this.logService = logService;
        this.request = request;
    }

    @EventListener
    public void onSuccess(AuthenticationSuccessEvent success) {
        UserAdapter userAdapter = (UserAdapter) success.getAuthentication().getPrincipal();
        User user = userRepository.findByEmailIgnoreCase(userAdapter.getUsername())
                .orElseThrow(EmailNotFoundException::new);
        user.setLoginAttempts(0);
        userRepository.save(user);
    }

    @EventListener
    public void onFailure(AbstractAuthenticationFailureEvent failures) {
        String email = (String) failures.getAuthentication().getPrincipal();
        User user = userRepository.findByEmailIgnoreCase(email).orElse(null);

        if (user == null) {
            logService.createLog(
                    Event.LOGIN_FAILED.name(),
                    email,
                    this.request.getRequestURI(),
                    this.request.getRequestURI()
            );
            return;
        }

        List<String> roles = getCurrentRoles(user);
        if (roles.contains("ROLE_ADMINISTRATOR")) {
            logService.createLog(
                    Event.LOGIN_FAILED.name(),
                    email,
                    this.request.getRequestURI(),
                    this.request.getRequestURI()
            );
            return;
        }

        if (!user.getIsLocked()) {
            if (user.getLoginAttempts() < 4) {
                user.setLoginAttempts(user.getLoginAttempts() + 1);
                logService.createLog(
                        Event.LOGIN_FAILED.name(),
                        user.getEmail(),
                        this.request.getRequestURI(),
                        this.request.getRequestURI()
                );
                userRepository.save(user);
            } else {
                logService.createLog(
                        Event.LOGIN_FAILED.name(),
                        user.getEmail(),
                        this.request.getRequestURI(),
                        this.request.getRequestURI()
                );
                logService.createLog(
                        Event.BRUTE_FORCE.name(),
                        user.getEmail(),
                        this.request.getRequestURI(),
                        this.request.getRequestURI()
                );
                user.setIsLocked(true);
                logService.createLog(
                        Event.LOCK_USER.name(),
                        user.getEmail(),
                        "Lock user " + user.getEmail(),
                        this.request.getRequestURI()
                );
                userRepository.save(user);
            }
        }


    }
    public List<String> getCurrentRoles(User user) {
        Iterator<Group> iterator = user.getUserGroups().iterator();
        List<String> roles = new ArrayList<>();

        while(iterator.hasNext()) {
            roles.add(iterator.next().getRole());
        }

        return roles;
    }
}
