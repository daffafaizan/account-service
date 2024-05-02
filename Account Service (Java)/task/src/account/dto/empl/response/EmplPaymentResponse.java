package account.dto.empl.response;

public class EmplPaymentResponse {
    private final Integer userId;
    private final String name;
    private final String lastname;
    private final String email;

    public EmplPaymentResponse(Integer userId, String name, String lastname, String email) {
        this.userId = userId;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
    }

    public Integer getId() {
        return this.userId;
    }
    public String getName() {
        return this.name;
    }
    public String getLastname() {
        return this.lastname;
    }
    public String getEmail() {
        return this.email;
    }
}