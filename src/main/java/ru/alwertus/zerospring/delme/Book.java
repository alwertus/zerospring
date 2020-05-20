package ru.alwertus.zerospring.delme;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.alwertus.zerospring.auth.User;
import ru.alwertus.zerospring.auth.UserService;

import javax.persistence.*;

@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
/*    @Transient
    @Autowired
    private UserService userService;*/

    @Id
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @Getter @Setter
    private String name;

    @NonNull
    @Getter @Setter
    private String accessRole;

    @Getter @Setter
    private Long ownerId;

    @Transient
    public boolean userHasAccess(User user) {
        return user.hasRole(getAccessRole()) ? true : false;
    }

    @Transient
    public boolean currentUserHasAccess() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        return userHasAccess(user);
    }

}
