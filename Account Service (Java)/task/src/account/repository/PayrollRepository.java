package account.repository;

import account.model.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Repository
public interface PayrollRepository extends JpaRepository<Payroll, String> {
    Optional<Payroll> findByEmployeeIgnoreCase(String employee);
    List<Payroll> findAllByEmployeeIgnoreCase(String employee);
}
