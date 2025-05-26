package com.example.GiangFroject.Service;

import com.example.GiangFroject.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    private UserService userService;

    @BeforeEach
    public void setUp() {
        userService = new UserService();
    }

    @Test
    public void testRegisterUser_Success() {
        User user = new User(null, "john", "123");
        User result = userService.registerUser(user);

        assertNotNull(result.getId());
        assertEquals("john", result.getUsername());
    }

    @Test
    public void testRegisterUser_Invalid() {
        User user = new User(null, null, "123");
        assertThrows(IllegalArgumentException.class, () -> {
            userService.registerUser(user);
        });
    }

    @Test
    public void testLogin_Success() {
        userService.registerUser(new User(null, "john", "123"));
        User user = userService.login("john", "123");

        assertNotNull(user);
    }

    @Test
    public void testLogin_Failure() {
        userService.registerUser(new User(null, "john", "123"));
        User user = userService.login("john", "wrongpass");

        assertNull(user);
    }
}
