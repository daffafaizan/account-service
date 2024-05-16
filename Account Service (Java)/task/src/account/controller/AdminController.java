package account.controller;

import account.dto.admin.response.DeleteUserResponseDTO;
import account.dto.admin.response.GetUserResponseDTO;
import account.model.User;
import account.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/user")
    public ResponseEntity<Object> getUsers() {
        List<User> users = adminService.getUsers();
        List<GetUserResponseDTO> response = new ArrayList<>();

        for (User user : users) {
            response.add(new GetUserResponseDTO(user));
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/user/{email}")
    public ResponseEntity<Object> deleteUser(@PathVariable String email) {
        adminService.deleteUser(email);
        return new ResponseEntity<>(new DeleteUserResponseDTO(email, "Deleted successfully!"), HttpStatus.OK);
    }
}
