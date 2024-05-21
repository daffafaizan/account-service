package account.service.security.impl;

import account.model.Log;
import account.repository.LogRepository;
import account.service.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecurityServiceImpl implements SecurityService {

    private final LogRepository logRepository;

    @Autowired
    public SecurityServiceImpl(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public List<Log> getLogs() {
        return logRepository.findAllOrderByIdAsc();
    }
}
