package account.controller;

import account.model.Log;
import account.service.log.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/security")
public class SecurityController {

    private final LogService logService;

    @Autowired
    public SecurityController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping("/")
    public ResponseEntity<Object> getLogs() {
        List<Log> response = logService.getLogs();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
