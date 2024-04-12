package account.controller;

import account.dto.auth.Request.SignupRequest;
import account.dto.auth.Response.SignupResponse;
import account.service.auth.AuthService;
import account.utils.Response;
import account.utils.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private final AuthService authService;

    public AuthController(AuthService authservice) {
        this.authService = authservice;
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(SignupRequest request) {
        SignupResponse response = authService.signup(request);
        return ResponseHandler.generateResponse(new Response("Successfully signed up!", HttpStatus.OK, "SUCCESS", response));
    }
}
