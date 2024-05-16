package account.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String type;
    private String role;
    @ManyToMany(mappedBy = "userGroups")
    private Set<User> users;

    public Group() {}

    public Group(String type, String role) {
        this.type = type;
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }

    public String getType() {
        return this.type;
    }
}
