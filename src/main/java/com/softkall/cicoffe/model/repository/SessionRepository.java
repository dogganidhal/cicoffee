package com.softkall.cicoffe.model.repository;

import com.softkall.cicoffe.model.entity.Session;

import java.util.Collection;
import java.util.UUID;

/**
 * @author Nidhal Dogga
 * @created 11/14/2020 2:35 PM
 * SoftKall™ All rights reserved.
 */


public interface SessionRepository extends AbstractRepository<Session, UUID> {
  Collection<Session> findAllByTeam_Members_Id(UUID memberId);
}
