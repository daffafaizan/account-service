package account.dto.admin.response;

public class DeleteUserResponseDTO {
    private String user;
    private String status;

    public DeleteUserResponseDTO(String email, String status) {
        this.user = email;
        this.status = status;
    }

    public String getUser() {
        return this.user;
    }
    public String getStatus() {
        return this.status;
    }
}
