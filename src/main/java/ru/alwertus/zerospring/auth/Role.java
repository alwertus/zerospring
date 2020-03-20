package ru.alwertus.zerospring.auth;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

//@RequiredArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Getter @Setter
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    public Role(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        return name.equals(((Role)obj).name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
