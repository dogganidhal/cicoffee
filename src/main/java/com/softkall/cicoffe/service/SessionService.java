package com.softkall.cicoffe.service;


import com.softkall.cicoffe.web.dto.input.CreateSessionDto;
import com.softkall.cicoffe.web.dto.output.SessionDto;

import java.util.Collection;
import java.util.UUID;

/**
 * @author Nidhal Dogga
 * @created 11/14/2020 2:33 PM
 * SoftKall™ All rights reserved.
 */


public interface SessionService {
  Collection<SessionDto> mySessions(UUID memberId);
  SessionDto createSession(CreateSessionDto request, UUID memberId);
  SessionDto confirmParticipation(UUID sessionId, UUID memberId);
  SessionDto retractParticipation(UUID sessionId, UUID memberId);
}
