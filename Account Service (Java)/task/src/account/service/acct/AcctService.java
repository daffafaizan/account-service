package account.service.acct;

import account.dto.acct.request.PayrollRequestDTO;

import java.util.List;

public interface AcctService {
    void uploadPayrolls(List<PayrollRequestDTO> requests);
    void updatePayroll(PayrollRequestDTO request);
}
