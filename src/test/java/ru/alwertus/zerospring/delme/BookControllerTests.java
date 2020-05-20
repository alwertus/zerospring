package ru.alwertus.zerospring.delme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.alwertus.zerospring.auth.UserService;

@SpringBootTest
public class BookControllerTests {

    @Autowired
    private UserService BookController;
}
