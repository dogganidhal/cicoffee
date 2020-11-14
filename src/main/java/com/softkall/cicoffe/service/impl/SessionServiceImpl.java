package com.softkall.cicoffe.service.impl;

import com.softkall.cicoffe.exception.NotFoundException;
import com.softkall.cicoffe.model.entity.Team;
import com.softkall.cicoffe.model.repository.SessionRepository;
import com.softkall.cicoffe.model.repository.TeamRepository;
import com.softkall.cicoffe.service.SessionService;
import com.softkall.cicoffe.web.dto.input.CreateSessionDto;
import com.softkall.cicoffe.web.dto.output.SessionDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;


/**
 * @author Nidhal Dogga
 * @created 11/14/2020 2:34 PM
 * SoftKall™ All rights reserved.
 */


@Service
@AllArgsConstructor
public class SessionServiceImpl implements SessionService {

  private final SessionRepository sessionRepository;
  private final TeamRepository teamRepository;

  @Override
  public SessionDto createSession(CreateSessionDto request, UUID userId) {
    Team team = teamRepository
            .findById(request.getTeamId())
            .orElseThrow(NotFoundException::new);
    return null;
  }

}
