package account.service.log;

import account.model.Event;
import account.model.Log;
import account.model.User;

import java.util.List;

public interface LogService {
    List<Log> getLogs();
    void createLog(String action, String subject, String object, String path);
}
