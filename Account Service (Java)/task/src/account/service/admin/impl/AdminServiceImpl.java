package account.service.admin.impl;

import account.model.User;
import account.repository.UserRepository;
import account.service.admin.AdminService;
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
}
