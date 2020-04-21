package ru.alwertus.zerospring;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.alwertus.zerospring.auth.User;
import ru.alwertus.zerospring.auth.UserService;

import javax.annotation.PostConstruct;

@Log4j2
@SpringBootApplication
public class Main {

    private static UserService userService;

    @Autowired
    public void setCompOne(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        log.info("2. postconstruct");
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
        log.info("----- ===== START ===== -----");

        User user = userService.createUser("user", "user123");
        User admin = userService.createUser("admin", "admin123");

        log.info(user.getRolesAsString() + " : " + user.hasRole("ROLE_ADMIN"));
        log.info(user.getRolesAsString() + " : " + user.hasRole("ROLE_USER"));
        log.info(user.getRolesAsString() + " : " + user.hasRole("ROLE_GOVNO"));
        userService.addRole(user,"ROLE_GOVNO");
        log.info(user.getRolesAsString() + " : " + user.hasRole("ROLE_GOVNO"));
        userService.removeRole(user, "ROLE_ADMIN");
        log.info(user.getRolesAsString());
        log.info("----- ===== END ===== -----");

//        userService.addRole(new User(), "ROLE_ADMIN");
    }

}