package account.controller;

import account.dto.auth.request.ChangePassRequest;
import account.dto.auth.request.SignupRequest;
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

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authservice) {
        this.authService = authservice;
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody @Valid SignupRequest request) throws JsonProcessingException {
        String response = authService.signup(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/changepass")
    public ResponseEntity<Object> changepass(@RequestBody @Valid ChangePassRequest request, @AuthenticationPrincipal UserDetails userDetails) throws JsonProcessingException {
        String response = authService.changepass(request, userDetails);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
