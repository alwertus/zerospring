package ru.alwertus.zerospring.auth;

import lombok.extern.log4j.Log4j2;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
//@ActiveProfiles("test")
public class UserServiceTests {
    public static final String username = "username";
    public static final String userpassw = "userpassword";
    public static final String userrole = "ROLE_TEST";
    public static User user;

    @Autowired
    private UserService userService;

    @Before
    public void beforeTests() {
        user = userService.createUser(username, userpassw);
    }

    @After
    public void afterTests() {
        userService.deleteUser(username);
    }

    @Test
    public void userServiceNotNull() {
        Assert.assertNotNull(userService);
    }

    @Test
    public void addAndRemoveRole() {
        userService.addRole(user, userrole);
        Assert.assertEquals(user.hasRole(userrole), true);
        userService.removeRole(user, userrole);
        Assert.assertEquals(user.hasRole(userrole), false);
        userService.removeRole(user, userrole);
        Assert.assertEquals(user.hasRole(userrole), false);
    }
}