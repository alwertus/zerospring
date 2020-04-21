package ru.alwertus.zerospring.delme;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.alwertus.zerospring.auth.User;

import java.util.List;

@Log4j2
@RestController
@Validated
public class BooksController {

    @Autowired
    private BookRepo bookRepo;

    // Find All
    @GetMapping("/books")
    List<Book> findAll() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        log.info(user.getName() + ":" + user.getRolesAsString());
        List<Book> findList = bookRepo.findAll();
        for(Book b : findList) {
            if (user.hasRole())
        }


        return bookRepo.findAll();
    }

    // Find ID
    @GetMapping("/books/{id}")
    Book findOne(@PathVariable Long id) {


        return bookRepo.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    // Create book
    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    Book newBook(@RequestBody Book newBook) {
        return bookRepo.save(newBook);
    }
}
