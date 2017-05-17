package com.example.entityrepository;

import com.example.users.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Praveen Thati on 5/17/17.
 */
public interface UsersRepository extends CrudRepository<User, Integer> {
    User findByEmail(String email);
}
