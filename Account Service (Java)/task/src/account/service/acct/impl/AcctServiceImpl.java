package account.service.acct.impl;

import account.dto.acct.request.PayrollRequestDTO;
import account.exception.acct.GeneralUploadPayrollException;
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
    public void uploadPayroll(List<PayrollRequestDTO> requests) {
        for (PayrollRequestDTO request: requests) {
            User user = userRepository.findByEmailIgnoreCase(request.getEmployee())
                    .orElseThrow(EmailNotFoundException::new);
            List<Payroll> payrolls = payrollRepository.findAllByEmployeeIgnoreCase(request.getEmployee());
            YearMonth period = YearMonth.parse(request.getPeriod(), periodFormat);

            for (Payroll payroll: payrolls) {
                if (payroll.getEmployee().equals(request.getEmployee()) && payroll.getPeriod().equals(period)) {
                    throw new GeneralUploadPayrollException();
                }
            }

            Payroll payroll = new Payroll();

            payroll.setEmployee(request.getEmployee());
            payroll.setName(user.getName());
            payroll.setLastname(user.getLastname());
            payroll.setPeriod(period);
            payroll.setSalary(request.getSalary());
            payrollRepository.save(payroll);
        }
    }
}
