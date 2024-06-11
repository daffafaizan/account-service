package account.service.admin.impl;

import account.dto.admin.request.AccessRequestDTO;
import account.dto.admin.request.ChangeRoleRequestDTO;
import account.exception.admin.*;
import account.exception.auth.EmailNotFoundException;
import account.model.Event;
import account.model.Group;
import account.model.User;
import account.repository.GroupRepository;
import account.repository.UserRepository;
import account.service.admin.AdminService;
import account.service.log.LogService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final LogService logService;
    private final HttpServletRequest request;

    @Autowired
    public AdminServiceImpl(UserRepository userRepository, GroupRepository groupRepository, LogService logService, HttpServletRequest request) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.logService = logService;
        this.request = request;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAllByOrderByIdAsc();
    }

    @Override
    @Transactional
    public void deleteUser(String email, UserDetails userDetails) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (userDetails.getUsername().equals(email)) {
            throw new RemoveAdministratorException();
        }
        if (userRepository.findByEmailIgnoreCase(email).isEmpty()) {
            throw new EmailNotFoundException();
        }
        userRepository.deleteByEmailIgnoreCase(email);
        logService.createLog(
                Event.DELETE_USER.name(),
                auth.getName(),
                email,
                this.request.getRequestURI()
        );
    }

    @Override
    @Transactional
    public User changeUserRole(ChangeRoleRequestDTO request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = request.getUser();
        String role = request.getRole();
        String operation = request.getOperation();

        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(EmailNotFoundException::new);

        List<String> currentRoles = getCurrentRoles(user);

        if (operation.equals("GRANT")) {
            if (currentRoles.contains("ROLE_ADMINISTRATOR") && (role.equals("USER") || role.equals("ACCOUNTANT") || role.equals("AUDITOR"))) {
                throw new InvalidRoleCombination();
            } else if ((currentRoles.contains("ROLE_USER") || currentRoles.contains("ACCOUNTANT") || currentRoles.contains("AUDITOR")) && role.equals("ADMINISTRATOR")) {
                throw new InvalidRoleCombination();
            } else {
                grantRole(user, role);
                logService.createLog(
                        Event.GRANT_ROLE.name(),
                        auth.getName(),
                        "Grant role " + role + " to " + user.getEmail(),
                        this.request.getRequestURI()
                );
                if (role.equals("ADMINISTRATOR")) {
                    user.setIsLocked(false);
                    user.setLoginAttempts(0);
                    userRepository.save(user);
                }
            }
        } else if (operation.equals("REMOVE")) {
            if (currentRoles.contains(String.format("ROLE_%s", role))) {
                if (role.equals("ADMINISTRATOR")) {
                    throw new RemoveAdministratorException();
                } else if (currentRoles.size() == 1) {
                    throw new UserMinimumOneRole();
                } else {
                    removeRole(user, role);
                    logService.createLog(
                            Event.REMOVE_ROLE.name(),
                            auth.getName(),
                            "Remove role " + role + " from " + user.getEmail(),
                            this.request.getRequestURI()
                    );
                }
            } else {
                throw new UserDoesNotHaveRoleException();
            }
        }

        userRepository.save(user);

        return user;
    }

    @Override
    public String changeAccess(AccessRequestDTO request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmailIgnoreCase(request.getUser())
                .orElseThrow(EmailNotFoundException::new);

        if (user.getUserGroups().contains(groupRepository.findByRole("ROLE_ADMINISTRATOR"))) {
            throw new LockAdministratorException();
        }

        if (request.getOperation().equals("LOCK")) {
            user.setIsLocked(true);
            logService.createLog(
                    Event.LOCK_USER.name(),
                    auth.getName(),
                    "Lock user " + user.getEmail(),
                    this.request.getRequestURI()
            );
            userRepository.save(user);
        } else if (request.getOperation().equals("UNLOCK")) {
            user.setIsLocked(false);
            logService.createLog(
                    Event.UNLOCK_USER.name(),
                    auth.getName(),
                    "Unlock user " + user.getEmail(),
                    this.request.getRequestURI()
            );
            userRepository.save(user);
        }

        return user.getEmail();
    }

    public List<String> getCurrentRoles(User user) {
        Iterator<Group> iterator = user.getUserGroups().iterator();
        List<String> roles = new ArrayList<>();

        while(iterator.hasNext()) {
            roles.add(iterator.next().getRole());
        }

        return roles;
    }

    private void removeRole(User user, String role) {
        switch (role) {
            case "ADMINISTRATOR" -> {
                Group group = groupRepository.findByRole("ROLE_ADMINISTRATOR");
                user.removeUserGroup(group);
            }
            case "ACCOUNTANT" -> {
                Group group = groupRepository.findByRole("ROLE_ACCOUNTANT");
                user.removeUserGroup(group);
            }
            case "USER" -> {
                Group group = groupRepository.findByRole("ROLE_USER");
                user.removeUserGroup(group);
            }
            case "AUDITOR" -> {
                Group group = groupRepository.findByRole("ROLE_AUDITOR");
                user.removeUserGroup(group);
            }
            default -> {
                throw new RoleNotFoundException();
            }
        }
    }

    private void grantRole(User user, String role) {
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
            case "AUDITOR" -> {
                Group group = groupRepository.findByRole("ROLE_AUDITOR");
                user.addUserGroups(group);
            }
            default -> {
                throw new RoleNotFoundException();
            }
        }
    }
}
