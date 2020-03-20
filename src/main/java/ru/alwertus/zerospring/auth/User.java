package ru.alwertus.zerospring.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;


@NoArgsConstructor

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String password;

    @Getter @Setter
    @ManyToMany(fetch = FetchType.EAGER/*, cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST
    }*/)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    // not null constructor
    public User(String name, String password, Set<Role> roles) {
        this.name = name;
        this.password = password;
        this.roles = roles;
    }

    public String getRolesAsString() {
        String result = "";
        for (Role r : roles) {
            result += "<" + r.getName() + ">";
        }
        return result;
    }

    @Transient
    public boolean hasRole(String roleName) {
        return roles.contains(new Role(roleName));
    }

}
