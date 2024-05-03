package account.service.acct;

import account.dto.acct.request.UploadPayrollRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface AcctService {
    String uploadPayroll(List<UploadPayrollRequest> requests) throws JsonProcessingException;
}
