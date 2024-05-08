package account.service.acct;

import account.dto.acct.request.UploadPayrollRequestDTO;

import java.util.List;

public interface AcctService {
    void uploadPayroll(List<UploadPayrollRequestDTO> requests);
}
