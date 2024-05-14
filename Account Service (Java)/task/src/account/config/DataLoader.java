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
            groupRepository.save(new Group("Administrative", "Administrator"));
            groupRepository.save(new Group("Business", "Accountant"));
            groupRepository.save(new Group("Business", "User"));
        } catch (Exception e) {

        }
    }
}