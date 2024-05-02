package account.service.user.impl;

import account.adapter.UserAdapter;
import account.exception.auth.EmailNotFoundException;
import account.model.User;
import account.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws EmailNotFoundException {
        User user = userRepository
                .findByEmail(email)
                .orElseThrow(EmailNotFoundException::new);

        return new UserAdapter(user);
    }
}

