package com.softkall.cicoffe.model.repository;

import com.softkall.cicoffe.model.entity.SessionParticipant;

import java.util.UUID;


/**
 * @author Nidhal Dogga
 * @created 11/15/2020 12:31 AM
 * SoftKall™ All rights reserved.
 */


public interface SessionParticipantRepository extends AbstractRepository<SessionParticipant, UUID> {
  void deleteByMember_Id(UUID memberId);
}
