package account.service.empl.impl;

import account.exception.auth.EmailNotFoundException;
import account.model.User;
import account.repository.UserRepository;
import account.service.empl.EmplService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class EmplServiceImpl implements EmplService {

    private final UserRepository userRepository;

    @Autowired
    public EmplServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getPayment(UserDetails details) {
        String email = details.getUsername();

        return userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(EmailNotFoundException::new);
    }
}
