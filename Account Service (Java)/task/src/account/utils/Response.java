package account.utils;

import org.springframework.http.HttpStatusCode;

public class Response {
    private final String message;
    private final HttpStatusCode code;
    private final String status;
    private final Object responseObject;

    public Response(String message, HttpStatusCode code, String status, Object responseObject) {
        this.message = message;
        this.code = code;
        this.status = status;
        this.responseObject= responseObject;
    }

    public String getMessage() {
        return this.message;
    }
    public HttpStatusCode getCode() {
        return this.code;
    }
    public String getStatus() {
        return this.status;
    }
    public Object getResponseObject() {
        return this.responseObject;
    }
}

