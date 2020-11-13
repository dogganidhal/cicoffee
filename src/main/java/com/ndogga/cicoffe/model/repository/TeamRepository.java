package com.ndogga.cicoffe.model.repository;

import com.ndogga.cicoffe.model.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author Nidhal Dogga
 * @since 11/13/2020 7:07 PM
 * Prize & Fun™ All rights reserved.
 */


public interface TeamRepository extends JpaRepository<Team, UUID> {

}
