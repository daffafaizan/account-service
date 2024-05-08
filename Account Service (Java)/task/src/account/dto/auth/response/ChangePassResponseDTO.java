package account.dto.auth.response;

public class ChangePassResponseDTO {
    private String email;
    private String status;

    public ChangePassResponseDTO(String email, String status) {
        this.email = email;
        this.status = status;
    }

    public String getEmail() {
        return this.email;
    }
    public String getStatus() {
        return this.status;
    }
}
