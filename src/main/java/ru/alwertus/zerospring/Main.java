package ru.alwertus.zerospring;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.alwertus.zerospring.auth.User;
import ru.alwertus.zerospring.auth.UserService;
import ru.alwertus.zerospring.delme.Book;
import ru.alwertus.zerospring.delme.BookRepo;

import javax.annotation.PostConstruct;

@Log4j2
@SpringBootApplication
public class Main {

    private static UserService userService;
    private static BookRepo bookRepo;

    @Autowired
    public void setCompOne(UserService userService, BookRepo bookRepo) {
        this.userService = userService;
        this.bookRepo = bookRepo;
    }

    @PostConstruct
    public void init() {
        log.info("2. postconstruct");
    }

    public static void firstStart() {
        log.info("----- ===== INIT START ===== -----");

        // create 2 users and roles
        User user = userService.createUser("user", "user123");
        User admin = userService.createUser("admin", "admin123");
        User asd = userService.createUser("asd", "asd123");

        userService.removeRole(asd, "ROLE_ANONYMOUS");
        userService.addRole(asd, "ROLE_ASD");

        userService.removeRole(user, "ROLE_ANONYMOUS");
        userService.addRole(user, "ROLE_USER");


        userService.removeRole(admin, "ROLE_ANONYMOUS");
        userService.addRole(admin, "ROLE_USER");
        userService.addRole(admin, "ROLE_ADMIN");

        log.info("USER = " + user.getRolesAsString());
        log.info("ADMIN = " + admin.getRolesAsString());
        log.info("ASD = " + asd.getRolesAsString());

        /*log.info(user.getRolesAsString() + " : " + user.hasRole("ROLE_ADMIN"));
        log.info(user.getRolesAsString() + " : " + user.hasRole("ROLE_USER"));
        log.info(user.getRolesAsString() + " : " + user.hasRole("ROLE_GOVNO"));
        userService.addRole(user,"ROLE_GOVNO");
        log.info(user.getRolesAsString() + " : " + user.hasRole("ROLE_GOVNO"));
        userService.removeRole(user, "ROLE_ADMIN");
        log.info(user.getRolesAsString());*/

        // create books
        String newBookName = "newBook228";
        Book book = bookRepo.findByName(newBookName).orElse(new Book(newBookName, "ROLE_USER"));
        bookRepo.save(book);
        log.info("Create book id=" + bookRepo.findByName(newBookName).get().getId());


        log.info("----- ===== INIT END ===== -----");
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class);

        firstStart();
    }

}