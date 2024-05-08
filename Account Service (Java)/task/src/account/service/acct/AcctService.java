package account.service.acct;

import account.dto.acct.request.PayrollRequestDTO;

import java.util.List;

public interface AcctService {
    void uploadPayroll(List<PayrollRequestDTO> requests);
}
