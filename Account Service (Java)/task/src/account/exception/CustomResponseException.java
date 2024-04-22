package account.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

public class CustomResponseException {
    @JsonProperty("error")
    private final String message;
    @JsonProperty("status")
    private final HttpStatus code;

    public CustomResponseException(String message, HttpStatus code) {
        this.message = message;
        this.code = code;
    }
}
