package com.example.users;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Praveen Thati on 5/17/17.
 */
@RestController
public class UsersController {

    private final UserDetailsService UserDetailsService;
    private final ViewingsService ViewingsService;

    public UsersController(UserDetailsService userDetailsService,ViewingsService viewingsService) {
        this.UserDetailsService = userDetailsService;
        this.ViewingsService = viewingsService;
    }

    @GetMapping("/users")
    public List<User> getUsers() {

        return UserDetailsService.getAllUsers();
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {

        return UserDetailsService.addUser(user);
    }

    @PatchMapping("/users/{id}/viewings")
    public void patchViewings(@PathVariable Long id, @RequestBody Viewing viewing) {

        ViewingsService.patchViewings(viewing, id);
    }

    @GetMapping("/users/{id}/recently-watched")
    public List<RecentlyViewedViewing> getUserRecentViewings(@PathVariable Long id) {

       return ViewingsService.getUsersRecentViewing(id);

    }


}