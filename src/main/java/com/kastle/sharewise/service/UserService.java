package com.kastle.sharewise.service;

import com.kastle.sharewise.exception.NotFoundException;
import com.kastle.sharewise.model.Team;
import com.kastle.sharewise.model.User;
import com.kastle.sharewise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * A Restful Controller servlet providing Restful access to <code>Team</code> data.
 * @see Team
 */
@RestController
public class UserService {
    /**
     * The User Repository
     * @see UserRepository
     */
    @Autowired
    private UserRepository userRepo;

    /**
     * HTTP Get request to return the list of all <code>User</code> records
     * @return the list of all <code>User</code> records
     */
    @GetMapping(value = RestUriConstants.GET_ALL_USERS)
    public Iterable<User> getAllUsers() {
        return userRepo.findAll();
    }

    /**
     * HTTP Get Request to get a single <code>User</code> record.
     * Has <code>TeamRepository</code> throw <code>NotFoundException</code> if record is not found.
     * @param id The id of the <code>User</code> to return sent as a URL path variable
     * @return The <code>User</code> record requested by it's id
     */
    @GetMapping(value = RestUriConstants.GET_USER)
    public User getUser(@PathVariable("id") long id) {
        return userRepo.findById(id).orElseThrow(()-> new NotFoundException());
    }

    /**
     * HTTP PUT Request to update an existing <code>User</code> record.
     * Requires the request to include <code>application/json</code> in header.
     * @param user The new values of the record with the exact <code>id</code> of the record to replace.
     * @return The updated record if found, else has <code>UserRepository</code> throw <code>NotFoundException</code>
     */
    @PostMapping(
            value = RestUriConstants.POST_USER,
            consumes = RestUriConstants.ACCEPTED_CONSUMER,
            produces = RestUriConstants.ACCEPTED_PRODUCER
    )
    public User addUser(@RequestBody User user) {
        try {
            if (userRepo.existsById(user.getId()))
                throw new NotFoundException();
            userRepo.save(user);
            return user.abstruse();
        } catch (NotFoundException e) {
            user.setRole("Nope");
            return user.abstruse();
        }
    }

    /**
     * HTTP POST request to add a new <code>User</code> record.
     * Requires the request to include <code>application/json</code> in header.
     * @param user The new <code>User</code> record data to add.
     * @return The created <code>User</code> record
     */
    @PutMapping(
            value = RestUriConstants.PUT_USER,
            consumes = RestUriConstants.ACCEPTED_CONSUMER,
            produces = RestUriConstants.ACCEPTED_PRODUCER
    )
    public User updateUser(@RequestBody User user) {
        userRepo.findById(user.getId()).orElseThrow(() -> new NotFoundException());
        User updateUser = user;
        userRepo.save(updateUser);
        return updateUser.abstruse();
    }

    /**
     * HTTP DELETE Request to delete a <code>User</code> record.
     * Requires the request to include <code>application/json</code> in header.
     * @param user The <code>User</code> to delete.
     * @return The data of the deleted <code>User</code> unless it was not found in which case
     * it has <code>UserRepository</code> throw a <code>NotFoundException</code>
     */
    @DeleteMapping(
            value = RestUriConstants.DELETE_USER,
            consumes = RestUriConstants.ACCEPTED_CONSUMER,
            produces = RestUriConstants.ACCEPTED_PRODUCER
    )
    //TODO make deletable with just id
    public User deleteUser(@RequestBody User user) {
        User deadUser = userRepo.findById(user.getId()).orElseThrow(()->new NotFoundException());
        userRepo.deleteById(deadUser.getId());
        return user.abstruse();
    }
}
