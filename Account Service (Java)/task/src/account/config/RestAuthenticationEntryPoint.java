package account.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Map;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setStatus(401);
        response.getOutputStream().println(new ObjectMapper().writeValueAsString(Map.of(
                "timestamp", LocalTime.now().toString(),
                "status", response.getStatus(),
                "error", "Unauthorized",
                "message", "User account is locked",
                "path", request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI).toString()
        )));
    }
}