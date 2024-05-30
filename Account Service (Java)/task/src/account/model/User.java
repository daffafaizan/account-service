package account.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

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
    @Email(regexp = "^.+@acme.com$")
    @Column(unique = true)
    @NotNull
    @NotBlank
    private String email;
    @JsonIgnore
    @NotNull
    @NotBlank
    private String password;
    @JsonIgnore
    private String authority;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "employee")
    @JsonIgnore
    private List<Payroll> payrolls;

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
    public String getAuthority() {
        return this.authority;
    }
    public void setAuthority(String authority) {
        this.authority = authority;
    }
    public List<Payroll> getPayrolls() {
        return this.payrolls;
    }
    public void setPayrolls(List<Payroll> payrolls) {
        this.payrolls = payrolls;
    }
    public void addPayroll(Payroll payroll) {
        this.payrolls.add(payroll);
    }
}
