package account.service.log.impl;

import account.model.Event;
import account.model.Log;
import account.repository.LogRepository;
import account.service.log.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;

    @Autowired
    public LogServiceImpl(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public List<Log> getLogs() {
        return logRepository.findAllByOrderByIdAsc();
    }

    @Override
    public void createLog(String action, String subject, String object, String path) {
        Log log = new Log();
        LocalDateTime date = LocalDateTime.now();

        log.setDate(date);
        log.setAction(action);
        log.setSubject(subject);
        log.setObject(object);
        log.setPath(path);

        logRepository.save(log);
    }
}
