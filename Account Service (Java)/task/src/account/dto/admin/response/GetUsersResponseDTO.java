package account.dto.admin.response;

import account.model.Group;
import account.model.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class GetUsersResponseDTO {
    private final Long id;
    private final String name;
    private final String lastname;
    private final String email;
    private final List<String> roles;

    public GetUsersResponseDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.lastname = user.getLastname();
        this.email = user.getEmail();
        this.roles = getRoles(user.getUserGroups());
    }

    private List<String> getRoles(Set<Group> userGroups) {
        Iterator<Group> iterator = userGroups.iterator();
        List<String> roles = new ArrayList<>();

        while(iterator.hasNext()) {
            roles.add(iterator.next().getRole());
        }

        return roles;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getLastname() {
        return this.lastname;
    }

    public String getEmail() {
        return this.email;
    }

    public List<String> getRoles() {
        return this.roles;
    }
}