package account.config;

import account.model.Group;
import account.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    private GroupRepository groupRepository;

    @Autowired
    public DataLoader(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
        createRoles();
    }

    private void createRoles() {
        try {
            groupRepository.save(new Group("ADMINISTRATIVE", "ROLE_ADMINISTRATOR"));
            groupRepository.save(new Group("BUSINESS", "ROLE_ACCOUNTANT"));
            groupRepository.save(new Group("BUSINESS", "ROLE_USER"));
        } catch (Exception e) {

        }
    }
}