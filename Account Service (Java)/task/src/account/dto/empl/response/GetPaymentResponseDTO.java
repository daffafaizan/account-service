package account.dto.empl.response;

import account.model.User;

public class GetPaymentResponseDTO {
    private final Long id;
    private final String name;
    private final String lastname;
    private final String email;

    public GetPaymentResponseDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.lastname = user.getLastname();
        this.email = user.getEmail();
    }

    public Long getId() {
        return this.id;
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
