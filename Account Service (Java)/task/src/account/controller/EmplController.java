package account.controller;

import account.model.Payroll;
import account.service.empl.EmplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/empl")
public class EmplController {

    private final EmplService emplService;

    @Autowired
    public EmplController(EmplService emplService) {
        this.emplService = emplService;
    }

    @GetMapping("/payment")
    public ResponseEntity<Object> getPaymentByPeriod(@AuthenticationPrincipal UserDetails details, @RequestParam(defaultValue = "") String period) {
        Object response;

        if (period.isBlank()) {
            response = emplService.getPayment(details);
        } else {
            response = emplService.getPaymentByPeriod(details, period);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
