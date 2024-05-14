package account.repository;

import account.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, String> {
    Group findByRole(String role);
}
