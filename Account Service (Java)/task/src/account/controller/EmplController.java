package account.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("empl")
public class EmplController {

    @GetMapping("/payment")
    public String getPayment() {
        return "Access to api/empl/payment granted!";
    }
}
