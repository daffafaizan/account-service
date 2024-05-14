package account.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String type;
    private String role;
    @ManyToMany(mappedBy = "userGroups")
    private Set<User> users;

    public Group(String type, String role) {
        this.type = type;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public String getType() {
        return type;
    }
}
