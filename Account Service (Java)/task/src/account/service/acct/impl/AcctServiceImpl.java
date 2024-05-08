package account.service.acct.impl;

import account.dto.acct.request.UploadPayrollRequestDTO;
import account.exception.auth.EmailNotFoundException;
import account.model.Payroll;
import account.model.User;
import account.repository.PayrollRepository;
import account.repository.UserRepository;
import account.service.acct.AcctService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class AcctServiceImpl implements AcctService {

    private final PayrollRepository payrollRepository;
    private final UserRepository userRepository;
    private final DateTimeFormatter periodFormat;

    @Autowired
    public AcctServiceImpl(PayrollRepository payrollRepository, UserRepository userRepository) {
        this.payrollRepository = payrollRepository;
        this.userRepository = userRepository;
        this.periodFormat = DateTimeFormatter.ofPattern("MM-yyyy");
    }

    @Override
    @Transactional
    public void uploadPayroll(List<UploadPayrollRequestDTO> requests) {
        for (UploadPayrollRequestDTO request: requests) {
            User user = userRepository.findByEmailIgnoreCase(request.getEmployee())
                    .orElseThrow(EmailNotFoundException::new);
            Payroll payroll = new Payroll();

            payroll.setEmployee(request.getEmployee());
            payroll.setName(user.getName());
            payroll.setLastname(user.getLastname());
            payroll.setPeriod(YearMonth.parse(request.getPeriod(), periodFormat));
            payroll.setSalary(request.getSalary());
            payrollRepository.save(payroll);
        }
    }
}
