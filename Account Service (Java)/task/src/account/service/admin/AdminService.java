package account.service.admin;

import account.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface AdminService {
    List<User> getUsers();
    void deleteUser(String email, UserDetails userDetails);
}
