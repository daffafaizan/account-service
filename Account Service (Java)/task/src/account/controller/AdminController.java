package account.controller;

import account.dto.admin.request.ChangeRoleRequestDTO;
import account.dto.admin.response.ChangeRoleResponseDTO;
import account.dto.admin.response.DeleteUserResponseDTO;
import account.dto.admin.response.GetUsersResponseDTO;
import account.model.User;
import account.service.admin.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
        List<GetUsersResponseDTO> response = new ArrayList<>();

        for (User user : users) {
            response.add(new GetUsersResponseDTO(user));
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/user/{email}")
    public ResponseEntity<Object> deleteUser(@PathVariable String email, @AuthenticationPrincipal UserDetails userDetails) {
        adminService.deleteUser(email, userDetails);
        return new ResponseEntity<>(new DeleteUserResponseDTO(email, "Deleted successfully!"), HttpStatus.OK);
    }

    @PutMapping("/user/role")
    public ResponseEntity<Object> updateUserRole(@RequestBody @Valid ChangeRoleRequestDTO request) {
        User response = adminService.changeUserRole(request);
        return new ResponseEntity<>(new ChangeRoleResponseDTO(response), HttpStatus.OK);
    }
}
