package account.config;

import account.model.Event;
import account.service.log.LogService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final LogService logService;
    private final HttpServletRequest request;

    @Autowired
    public CustomAccessDeniedHandler(LogService logService, HttpServletRequest request) {
        this.logService = logService;
        this.request = request;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logService.createLog(
                Event.ACCESS_DENIED.name(),
                auth.getName(),
                this.request.getRequestURI(),
                this.request.getRequestURI()
        );
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied!");
    }
}
