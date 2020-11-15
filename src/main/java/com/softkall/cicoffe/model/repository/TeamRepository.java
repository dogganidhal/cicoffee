package com.softkall.cicoffe.model.repository;

import com.softkall.cicoffe.model.entity.Team;

import java.util.Collection;
import java.util.UUID;

/**
 * @author Nidhal Dogga
 * @created 11/13/2020 7:07 PM
 * SoftKall™ All rights reserved.
 */


public interface TeamRepository extends AbstractRepository<Team, UUID> {
  Collection<Team> findAllByMembers_Id(UUID memberId);
}
