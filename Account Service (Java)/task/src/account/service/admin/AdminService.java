package account.service.admin;

import account.dto.admin.request.AccessRequestDTO;
import account.dto.admin.request.ChangeRoleRequestDTO;
import account.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface AdminService {
    List<User> getUsers();
    void deleteUser(String email, UserDetails userDetails);
    User changeUserRole(ChangeRoleRequestDTO request);
    String changeAccess(AccessRequestDTO request);
}
