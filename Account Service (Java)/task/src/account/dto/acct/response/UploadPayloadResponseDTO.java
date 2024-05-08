package account.dto.acct.response;

public class UploadPayloadResponseDTO {
    private String status;

    public UploadPayloadResponseDTO(String status) {
        this.status = status;
    }
    public String getStatus() {
        return this.status;
    }
}
