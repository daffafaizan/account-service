package account.controller;

import account.dto.auth.request.ChangePassRequest;
import account.dto.auth.request.SignupRequestDTO;
import account.model.User;
import account.service.auth.AuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private final AuthService authService;

    public AuthController(AuthService authservice) {
        this.authService = authservice;
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody @Valid SignupRequestDTO request) throws JsonProcessingException {
        User response = authService.signup(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/changepass")
    public ResponseEntity<Object> changepass(@RequestBody @Valid ChangePassRequest request, @AuthenticationPrincipal UserDetails userDetails) throws JsonProcessingException {
        String response = authService.changepass(request, userDetails);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
