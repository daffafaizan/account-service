package account.repository;

import account.model.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;
import java.util.List;

@Repository
public interface PayrollRepository extends JpaRepository<Payroll, Long> {
    List<Payroll> findAllByEmployeeIgnoreCaseOrderByPeriodDesc(String employee);
    Payroll findByEmployeeIgnoreCaseAndPeriod(String employee, YearMonth period);
}
