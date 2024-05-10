package account.service.auth;

<<<<<<< HEAD
import account.dto.auth.request.ChangePassRequest;
import account.dto.auth.request.SignupRequest;
=======
import account.dto.auth.request.SignupRequestDTO;
import account.model.User;
>>>>>>> account-service-master
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {
    String changepass(ChangePassRequest request, UserDetails userDetails) throws JsonProcessingException;
    User signup(SignupRequestDTO request) throws JsonProcessingException;
}
