package com.kastle.sharewise.repository;

import com.kastle.sharewise.model.Team;
import org.springframework.data.repository.CrudRepository;

/**
 * Interface for Restful access to stored Teams
 */
public interface TeamRepository extends CrudRepository<Team, Long> {}
