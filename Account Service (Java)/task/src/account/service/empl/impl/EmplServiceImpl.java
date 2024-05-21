package account.service.empl.impl;

import account.exception.acct.PeriodIsInvalidException;
import account.exception.auth.EmailNotFoundException;
import account.model.Payroll;
import account.model.User;
import account.repository.PayrollRepository;
import account.repository.UserRepository;
import account.service.empl.EmplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class EmplServiceImpl implements EmplService {

    private final UserRepository userRepository;
    private final PayrollRepository payrollRepository;
    private final DateTimeFormatter periodFormat;

    @Autowired
    public EmplServiceImpl(UserRepository userRepository, PayrollRepository payrollRepository) {
        this.userRepository = userRepository;
        this.payrollRepository = payrollRepository;
        this.periodFormat = DateTimeFormatter.ofPattern("MM-yyyy");
    }

    @Override
    public List<Payroll> getPayment(UserDetails details) {
        User user = userRepository.findByEmailIgnoreCase(details.getUsername())
                .orElseThrow(EmailNotFoundException::new);

        return payrollRepository.findAllByEmployeeOrderByPeriodDesc(user);
    }

    @Override
    public Payroll getPaymentByPeriod(UserDetails details, String period) {
        User user = userRepository.findByEmailIgnoreCase(details.getUsername())
                .orElseThrow(EmailNotFoundException::new);

        if (!isPeriodValid(period)) {
            throw new PeriodIsInvalidException();
        }
        YearMonth convertedPeriod = YearMonth.parse(period, periodFormat);

        return payrollRepository.findByEmployeeAndPeriod(user, convertedPeriod);
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

}
