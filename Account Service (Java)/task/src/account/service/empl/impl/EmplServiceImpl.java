package account.service.empl.impl;

import account.model.Payroll;
import account.repository.PayrollRepository;
import account.service.empl.EmplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class EmplServiceImpl implements EmplService {

    private final PayrollRepository payrollRepository;
    private final DateTimeFormatter periodFormat;

    @Autowired
    public EmplServiceImpl(PayrollRepository payrollRepository) {
        this.payrollRepository = payrollRepository;
        this.periodFormat = DateTimeFormatter.ofPattern("MM-yyyy");
    }

    @Override
    public List<Payroll> getPayment(UserDetails details) {
        return payrollRepository.findAllByEmployeeIgnoreCase(details.getUsername());
    }

    @Override
    public Payroll getPaymentByPeriod(UserDetails details, String period) {
        YearMonth convertedPeriod = YearMonth.parse(period, periodFormat);
        return payrollRepository.findByEmployeeIgnoreCaseAndPeriod(details.getUsername(), convertedPeriod);
    }

}
