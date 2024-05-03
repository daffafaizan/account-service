package account.service.acct.impl;

import account.dto.acct.request.UploadPayrollRequest;
import account.dto.acct.response.UploadPayrollResponse;
import account.exception.auth.EmailNotFoundException;
import account.model.Payroll;
import account.model.User;
import account.repository.PayrollRepository;
import account.repository.UserRepository;
import account.service.acct.AcctService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcctServiceImpl implements AcctService {

    private final PayrollRepository payrollRepository;
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public AcctServiceImpl(PayrollRepository payrollRepository, UserRepository userRepository) {
        this.payrollRepository = payrollRepository;
        this.userRepository = userRepository;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    @Transactional
    public String uploadPayroll(List<UploadPayrollRequest> requests) throws JsonProcessingException {
        for (UploadPayrollRequest request: requests) {
            User user = userRepository.findByEmailIgnoreCase(request.getEmployee())
                    .orElseThrow(EmailNotFoundException::new);
            Payroll payroll = new Payroll();

            payroll.setName(user.getName());
            payroll.setLastname(user.getLastname());
            payroll.setPeriod(request.getPeriod());
            payroll.setSalary(request.getSalary());
            payrollRepository.save(payroll);
        }

        UploadPayrollResponse response = new UploadPayrollResponse("Added successfully!");

        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
    }
}
