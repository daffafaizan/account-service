package account.service.admin;

import account.model.User;

import java.util.List;

public interface AdminService {
    List<User> getUsers();
    void deleteUser(String email);
}
