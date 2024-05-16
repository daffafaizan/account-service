package account.service.admin.impl;

import account.exception.auth.EmailNotFoundException;
import account.model.User;
import account.repository.UserRepository;
import account.service.admin.AdminService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;

    @Autowired
    public AdminServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAllByOrderByIdAsc();
    }

    @Override
    @Transactional
    public void deleteUser(String email) {
        if (userRepository.findByEmailIgnoreCase(email).isEmpty()) {
            throw new EmailNotFoundException();
        }
        userRepository.deleteByEmailIgnoreCase(email);
    }
}
