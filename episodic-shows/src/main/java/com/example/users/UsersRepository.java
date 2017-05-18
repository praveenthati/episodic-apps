package com.example.users;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Praveen Thati on 5/17/17.
 */
public interface UsersRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
