package ru.alwertus.zerospring.delme;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.alwertus.zerospring.auth.User;
import ru.alwertus.zerospring.auth.UserService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Log4j2
@RestController
@Validated
public class BookController {

    @Autowired
    private BookRepo bookRepo;

    // Find All available books
    @GetMapping("/books")
    List<Book> findAll() {
        List<Book> findList = bookRepo.findAll();
        List<Book> resultList = new LinkedList<>();
        for(Book b : findList) {
            if (b.currentUserHasAccess())
                resultList.add(b);
        }

        return resultList;
    }

    // Find 1 by ID
    @GetMapping("/books/{id}")
    Book findOne(@PathVariable Long id) {
        Book returnBook =  bookRepo.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        if (returnBook.currentUserHasAccess())
            return returnBook;
        else
            throw new NoAccessException(returnBook.getAccessRole());
    }

    // Create book
    @PutMapping("/books")
//    @ResponseStatus(HttpStatus.CREATED)
    Book newBook(@RequestBody Book book) {
        log.info("Create Book " + book.getName());

        Book repoBook = bookRepo.findByName(book.getName()).orElse(book);
        return bookRepo.save(repoBook);
/*
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        log.info(user.getRolesAsString());

        if (user.hasRole("ROLE_ADMIN"))
            return bookRepo.save(newBook);
        else
//            return new Book("NAME-ERROR","NO_ROLE");
            throw new NoAccessException("ROLE_ADMIN");*/
    }
    // TEST:
    // curl -X PUT localhost:5188/books -H "Content-type:application/json" -d {\"name\":\"consoleBook11\",\"accessRole\":\"ROLE_USER\"} -u admin:admin123
}
