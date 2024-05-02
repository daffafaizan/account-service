package account.dto.auth.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "id",
        "name",
        "lastname",
        "email"
})
public class SignupResponse {
    @JsonProperty("id")
    private final Integer userId;
    @JsonProperty("name")
    private final String name;
    @JsonProperty("lastname")
    private final String lastname;
    @JsonProperty("email")
    private final String email;

    public SignupResponse(Integer userId, String name, String lastname, String email) {
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
