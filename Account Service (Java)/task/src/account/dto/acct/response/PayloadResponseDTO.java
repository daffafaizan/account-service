package account.dto.acct.response;

public class PayloadResponseDTO {
    private String status;

    public PayloadResponseDTO(String status) {
        this.status = status;
    }
    public String getStatus() {
        return this.status;
    }
}
