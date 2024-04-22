package account.controller;

import account.dto.auth.request.SignupRequest;
import account.dto.auth.response.SignupResponse;
import account.model.User;
import account.service.auth.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> signup(@RequestBody @Valid SignupRequest request) throws Exception {
        SignupResponse response = authService.signup(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
