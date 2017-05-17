package com.example.users;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Praveen Thati on 5/17/17.
 */
@RestController
public class UsersController {

    private final UserDetailsService UserDetailsService;

    public UsersController(UserDetailsService userDetailsService) {
        this.UserDetailsService = userDetailsService;
    }

    @GetMapping("/users")
    public List<User> getUsers() {

        return UserDetailsService.getAllUsers();
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {

        return UserDetailsService.addUser(user);
    }

}