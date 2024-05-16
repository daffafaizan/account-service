package account.dto.admin.response;

public class DeleteUserResponseDTO {
    private String email;
    private String status;

    public DeleteUserResponseDTO(String email, String status) {
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
