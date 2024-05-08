package account.controller;

import account.dto.acct.request.PayrollRequestDTO;
import account.dto.acct.response.PayrollResponseDTO;
import account.service.acct.AcctService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Object> uploadPayrolls(@RequestBody List<@Valid PayrollRequestDTO> requests) {
        acctService.uploadPayrolls(requests);
        return new ResponseEntity<>(new PayrollResponseDTO("Added successfully!"), HttpStatus.OK);
    }

    @PutMapping("/payments")
    public ResponseEntity<Object> updatePayroll(@RequestBody @Valid PayrollRequestDTO request) {
        acctService.updatePayroll(request);
        return new ResponseEntity<>(new PayrollResponseDTO("Updated successfully!"), HttpStatus.OK);
    }
}
