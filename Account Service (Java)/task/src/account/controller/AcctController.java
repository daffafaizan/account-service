package account.controller;

import account.dto.acct.request.PayrollRequestDTO;
import account.dto.acct.response.PayloadResponseDTO;
import account.service.acct.AcctService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/acct")
public class AcctController {

    private final AcctService acctService;

    @Autowired
    public AcctController(AcctService acctService) {
        this.acctService = acctService;
    }

    @PostMapping("/payments")
    public ResponseEntity<Object> uploadPayrolls(@RequestBody List<@Valid PayrollRequestDTO> requests) throws JsonProcessingException {
        acctService.uploadPayroll(requests);
        return new ResponseEntity<>(new PayloadResponseDTO("Added successfully!"), HttpStatus.OK);
    }
}
