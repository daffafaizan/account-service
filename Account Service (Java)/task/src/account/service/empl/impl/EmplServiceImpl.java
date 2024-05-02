package account.service.empl.impl;

import account.dto.empl.response.EmplPaymentResponse;
import account.exception.auth.EmailNotFoundException;
import account.model.User;
import account.repository.UserRepository;
import account.service.empl.EmplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class EmplServiceImpl implements EmplService {

    @Autowired
    private final UserRepository userRepository;

    public EmplServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public EmplPaymentResponse getPayment(UserDetails details) {
        String email = details.getUsername();

        User user = userRepository.findByEmail(email)
                .orElseThrow(EmailNotFoundException::new);

        Integer userId = user.getId();
        String name = user.getName();
        String lastname = user.getLastname();

        return new EmplPaymentResponse(userId, name, lastname, email);
    }
}
