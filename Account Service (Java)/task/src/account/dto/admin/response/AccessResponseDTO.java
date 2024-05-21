package account.dto.admin.response;

public class AccessResponseDTO {
    private final String status;

    public AccessResponseDTO(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }
}
