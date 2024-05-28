package account.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String lastname;
    @Column(unique = true)
    @NotNull
    @NotBlank
    private String email;
    @JsonIgnore
    @NotNull
    @NotBlank
    private String password;
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    },
    fetch = FetchType.EAGER)
    @JoinTable(name = "user_groups",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    @JsonProperty("roles")
    private Set<Group> userGroups = new HashSet<>();
    @JsonIgnore
    private Boolean isLocked;
    @JsonIgnore
    private Integer loginAttempts;

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastname() {
        return this.lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Set<Group> getUserGroups() {
        return this.userGroups;
    }
    public void setUserGroups(Set<Group> userGroups) {
        this.userGroups = userGroups;
    }
    public void addUserGroups(Group group) {
        this.userGroups.add(group);
    }
    public void removeUserGroup(Group group) {
        this.userGroups.remove(group);
    }
    public Boolean getIsLocked() {
        return this.isLocked;
    }
    public void setIsLocked(Boolean isLocked) {
        this.isLocked = isLocked;
    }
    public Integer getLoginAttempts() {
        return this.loginAttempts;
    }
    public void setLoginAttempts(Integer loginAttempts) {
        this.loginAttempts = loginAttempts;
    }
}
