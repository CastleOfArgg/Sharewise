package com.kastle.sharewise.service;

import com.kastle.sharewise.exception.FormatException;
import com.kastle.sharewise.exception.NotFoundException;
import com.kastle.sharewise.model.Greeting;
import com.kastle.sharewise.repository.GreetingRepository;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Example Rest Controller servlet. All Rest paths are defined in <code>RestUriConstants</code>.
 * Shows how to take in basic Restful HTTP requests and send back appropriate responses.
 * All rest openings take in path variables as parameters except <code>AddGreeting</code>
 * which requires an HTTP body defining a new <code>Greeting</code> obj.
 * @see RestUriConstants
 * @see GreetingRepository
 */
@RestController
public class GreetingService {
    /**
     * Logger for logging requests, errors, actions, warnings and responses
     */
    private static final Logger logger = LoggerFactory.getLogger(GreetingService.class);

    /**
     * Connected <code>Greeting</code> Repositories
     * @see GreetingRepository
     */
    @Autowired
    public GreetingRepository greetingRepo;

    /**
     * Accepts an path (URL) variable for a <code>Greeting</code> record and returns that <code>Greeting</code>.
     * Will have <code>GreetingRepo</code> throw NotFoundException if <code>Greeting</code> is not found.
     * @param id The <code>Greeting</code> record id
     * @return The <code>Greeting</code> record with the requested id
     */
    @GetMapping(value = RestUriConstants.GET_GREETING)
    public Greeting getGreeting(@PathVariable("id") String id) {
        logger.info("Request for getGreeting. id = " + id);

        try {
            return greetingRepo.findById(Long.parseLong(id)).orElseThrow(() -> new NotFoundException());
        } catch (NumberFormatException e) {
            logger.error(e.getMessage());
            throw new FormatException();
        }
    }

    /**
     * @return An Array of all <code>Greeting</code> records
     */
    @GetMapping(value = RestUriConstants.GET_ALL_GREETING)
    public Iterable<Greeting> getAllGreetings() {
        logger.info("Request for getAllGreetings.");
        return greetingRepo.findAll();
    }

    /**
     * Creates a new <code>Greeting</code> record and returns that <code>Greeting</code> instance;
     * @param body An HTTP Post Request body which must include required Greeting data
     * @return The created <code>Greeting</code> record
     */
    @PostMapping(value = RestUriConstants.CREATE_GREETING)
    public Greeting createGreeting(@RequestBody Greeting body) {
        body.setTimeStamp(new Date());
        greetingRepo.save(body);
        return body;
    }

    @PutMapping(value = RestUriConstants.PUT_GREETING)
    public Greeting putGreeting(@RequestBody Greeting body) {
        logger.info("Request for putGreeting. id = " + body.getId());

        try {
            Greeting greeting = greetingRepo.findById(body.getId()).orElseThrow(() -> new NotFoundException());
            greeting.setEmail(body.getEmail());
            greeting.setPassword(body.getPassword());
            greeting.setName(body.getName());
            greetingRepo.save(greeting);
            return greeting;
        } catch (NumberFormatException e) {
            logger.error(e.getMessage());
            throw new FormatException();
        }
    }

    /**
     * Deletes a <code>Greeting</code> record with the requested id
     * @param id The id of the <code>Greeting</code> to delete
     * @return The deleted record
     */
    @DeleteMapping(value = RestUriConstants.DELETE_GREETING)
    public Greeting deleteGreeting(@PathVariable("id") String id) {
        logger.info("Request for deleteGreeting. id = " + id);

        try {
            Long greetId = Long.parseLong(id);
            Greeting greet = greetingRepo.findById(greetId).orElseThrow(() -> new NotFoundException());
            greetingRepo.deleteById(greetId);
            return  greet;
        } catch (NumberFormatException e) {
            logger.error(e.getMessage());
            throw new FormatException();
        }
    }
}
