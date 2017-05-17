package com.example.users;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Praveen Thati on 5/17/17.
 */
@RequestMapping("/users")
@RestController
public class UsersController {

    private UserDetailsService UserDetailsService;

    public UsersController(UserDetailsService userDetailsService) {
        this.UserDetailsService = userDetailsService;
    }

    @GetMapping("")
    public List<User> getUsers(HttpServletRequest request) {

        return UserDetailsService.getAllUsers();
    }

}