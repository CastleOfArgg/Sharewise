package com.kastle.sharewise.repository;

import com.kastle.sharewise.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Interface for Restful access to stored Users
 */
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Calls and SQL query to find and return the user with the requested username
     * @param username The username to query all Users for
     * @return The User with the requested username else NULL
     */
    //@Query("SELECT u FROM user u WHERE u.username = :username;")
    //public User loadUserByUsername(@Param("username") String username);
}
