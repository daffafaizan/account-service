package account.dto.acct.response;

public class PayrollResponseDTO {
    private String status;

    public PayrollResponseDTO(String status) {
        this.status = status;
    }
    public String getStatus() {
        return this.status;
    }
}
