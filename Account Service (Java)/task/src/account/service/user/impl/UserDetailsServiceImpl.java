package account.service.user.impl;

import account.adapter.UserAdapter;
import account.exception.auth.EmailNotFoundException;
import account.model.Group;
import account.model.User;
import account.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmailIgnoreCase(email).orElseThrow(() -> new UsernameNotFoundException("User not found!"));

        return new UserAdapter(user, getAuthorities(user));
    }

    public List<SimpleGrantedAuthority> getAuthorities(User user) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Group group : user.getUserGroups()) {
            authorities.add(new SimpleGrantedAuthority(group.getRole()));
        }

        return authorities;
    };
}

