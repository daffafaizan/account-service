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
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<Object> signup(@RequestBody SignupRequest request) {
        try {
            SignupResponse response = authService.signup(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
