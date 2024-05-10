package account.service.empl.impl;

import account.model.Payroll;
import account.model.User;
import account.repository.PayrollRepository;
import account.repository.UserRepository;
import account.service.empl.EmplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmplServiceImpl implements EmplService {

    private final PayrollRepository payrollRepository;

    @Autowired
    public EmplServiceImpl(PayrollRepository payrollRepository) {
        this.payrollRepository = payrollRepository;
    }

    @Override
    public List<Payroll> getPayment(UserDetails details) {

        return payrollRepository.findAllByEmployeeIgnoreCase(details.getUsername());
    }

}
