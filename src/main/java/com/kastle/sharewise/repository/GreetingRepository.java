package com.kastle.sharewise.repository;

import com.kastle.sharewise.model.Greeting;
import org.springframework.data.repository.CrudRepository;

//See UserRepository on examples on how to add functions
/**
 * Interface for Restful access to stored Greetings
 */
public interface GreetingRepository extends CrudRepository<Greeting, Long> {}
