package account.dto.admin.response;

public class AccessResponseDTO {
    private final String status;

    public AccessResponseDTO(String user, String status) {
        this.status = createStatus(user, status);
    }

    public String getStatus() {
        return this.status;
    }

    private String createStatus(String user, String status) {
        if (status.equals("LOCK")) {
            return "User " + user + " locked!";
        } else {
            return "User " + user + " unlocked!";
        }
    }
}
