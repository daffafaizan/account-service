package account.repository;

import account.model.Payroll;
import account.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;
import java.util.List;

@Repository
public interface PayrollRepository extends JpaRepository<Payroll, Long> {
    List<Payroll> findAllByEmployeeOrderByPeriodDesc(User employee);
    Payroll findByEmployeeAndPeriod(User employee, YearMonth period);
}
