package account.service.acct.impl;

import account.dto.acct.request.PayrollRequestDTO;
import account.exception.acct.GeneralUploadPayrollException;
import account.exception.acct.NegativeSalaryException;
import account.exception.acct.PeriodIsInvalidException;
import account.exception.auth.EmailNotFoundException;
import account.model.Payroll;
import account.model.User;
import account.repository.PayrollRepository;
import account.repository.UserRepository;
import account.service.acct.AcctService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    public void uploadPayrolls(List<PayrollRequestDTO> requests) {
        for (PayrollRequestDTO request: requests) {

            if (!isPeriodValid(request.getPeriod())) {
                throw new PeriodIsInvalidException();
            }
            if (!isSalaryValid(request.getSalary())) {
                throw new NegativeSalaryException();
            }

            YearMonth period = YearMonth.parse(request.getPeriod(), periodFormat);

            User user = userRepository.findByEmailIgnoreCase(request.getEmployee())
                    .orElseThrow(EmailNotFoundException::new);

            if (isEmployeeWithPeriodExist(user, period)) {
                throw new GeneralUploadPayrollException();
            }


            Payroll payroll = new Payroll();

            payroll.setEmployee(user);
            payroll.setPeriod(period);
            payroll.setSalary(request.getSalary());
            payrollRepository.save(payroll);
        }
    }

    @Override
    public void updatePayroll(PayrollRequestDTO request) {
        User user = userRepository.findByEmailIgnoreCase(request.getEmployee())
                .orElseThrow(EmailNotFoundException::new);

        if (!isPeriodValid(request.getPeriod())) {
            throw new PeriodIsInvalidException();
        }

        YearMonth period = YearMonth.parse(request.getPeriod(), periodFormat);
        BigDecimal salary = request.getSalary();

        Payroll payroll = payrollRepository.findByEmployeeAndPeriod(user, period);
        payroll.setSalary(salary);
        payrollRepository.save(payroll);
    }

    private boolean isEmployeeWithPeriodExist(User employee, YearMonth period) {
        return payrollRepository.findByEmployeeAndPeriod(employee, period) != null;
    }

    private boolean isPeriodValid(String period) {
        YearMonth p;
        try {
            p = YearMonth.parse(period, periodFormat);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isSalaryValid(BigDecimal salary) {
        return salary.signum() > 0;
    }

}
