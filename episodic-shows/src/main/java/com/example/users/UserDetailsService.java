package com.example.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Praveen Thati on 5/17/17.
 */
@Service
public class UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    public List<User> getAllUsers(){

        List<User> list = new ArrayList<>();
        usersRepository.findAll().forEach(list::add);
        return list;

    }

    public User addUser(User user){
        return usersRepository.save(user);
    }
}
