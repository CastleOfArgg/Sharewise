package com.kastle.sharewise.service;

import com.kastle.sharewise.exception.NotFoundException;
import com.kastle.sharewise.model.Team;
import com.kastle.sharewise.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * A Restful Controller servlet providing Restful access to <code>Team</code> data.
 * @see Team
 */
@RestController
public class TeamService {
    /**
     * The Team Repository
     * @see TeamRepository
     */
    @Autowired
    private TeamRepository teamRepo;

    /**
     * HTTP Get request to return the list of all <code>Team</code> records
     * @return the list of all <code>Team</code> records
     */
    @GetMapping(value = RestUriConstants.GET_ALL_TEAM)
    public Iterable<Team> getAllTeams() {
        return teamRepo.findAll();
    }

    /**
     * HTTP Get Request to get a single <code>Team</code> record.
     * Has <code>TeamRepository</code> throw <code>NotFoundException</code> if record is not found.
     * @param id The id of the <code>Team</code> to return sent as a URL path variable
     * @return The <code>Team</code> record requested by it's id
     */
    @GetMapping(value = RestUriConstants.GET_TEAM)
    public Team getTeam(@PathVariable("id") long id) {
        return teamRepo.findById(id).orElseThrow(()->new NotFoundException());
    }

    /**
     * HTTP PUT Request to update an existing <code>Team</code> record.
     * Requires the request to include <code>application/json</code> in header.
     * @param team The new values of the record with the exact <code>id</code> of the record to replace.
     * @return The updated record if found, else has <code>TeamRepository</code> throw <code>NotFoundException</code>
     */
    @PutMapping(
            value = RestUriConstants.PUT_TEAM,
            consumes = RestUriConstants.ACCEPTED_CONSUMER,
            produces = RestUriConstants.ACCEPTED_PRODUCER
    )
    public Team updateTeam(@RequestBody Team team) {
        teamRepo.findById(team.getId()).orElseThrow(()->new NotFoundException());
        teamRepo.save(team);
        return team;
    }

    /**
     * HTTP POST request to add a new <code>Team</code> record.
     * Requires the request to include <code>application/json</code> in header.
     * @param team The new <code>Team</code> record data to add.
     * @return The created <code>Team</code> record
     */
    @PostMapping(
            value = RestUriConstants.POST_TEAM,
            consumes = RestUriConstants.ACCEPTED_CONSUMER,
            produces = RestUriConstants.ACCEPTED_PRODUCER
    )
    //TODO better exception
    public Team addTeam(@RequestBody Team team) throws Exception {
        if (teamRepo.existsById(team.getId()))
            throw new Exception();
        teamRepo.save(team);
        return team;
    }

    /**
     * HTTP DELETE Request to delete a <code>Team</code> record.
     * Requires the request to include <code>application/json</code> in header.
     * @param team The <code>Team</code> to delete.
     * @return The data of the deleted <code>Team</code> unless it was not found in which case
     * it has <code>TeamRepository</code> throw a <code>NotFoundException</code>
     */
    @DeleteMapping(
            value = RestUriConstants.DELETE_TEAM,
            consumes = RestUriConstants.ACCEPTED_CONSUMER,
            produces = RestUriConstants.ACCEPTED_PRODUCER
    )
    public Team deleteTeam(@RequestBody Team team) {
        teamRepo.findById(team.getId()).orElseThrow(() -> new NotFoundException());
        teamRepo.deleteById(team.getId());
        return team;
    }
}
