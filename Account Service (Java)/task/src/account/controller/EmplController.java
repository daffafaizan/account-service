package account.controller;

import account.dto.empl.response.GetPaymentResponseDTO;
import account.model.User;
import account.service.empl.EmplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/empl")
public class EmplController {

    private final EmplService emplService;

    @Autowired
    public EmplController(EmplService emplService) {
        this.emplService = emplService;
    }

    @GetMapping("/payment")
    public ResponseEntity<Object> getPayment(@AuthenticationPrincipal UserDetails details) {
        User response = emplService.getPayment(details);
        return new ResponseEntity<>(new GetPaymentResponseDTO(response), HttpStatus.OK);
    }
}
