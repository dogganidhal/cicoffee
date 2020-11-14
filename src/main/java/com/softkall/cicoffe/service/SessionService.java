package com.softkall.cicoffe.service;


import com.softkall.cicoffe.web.dto.input.CreateSessionDto;
import com.softkall.cicoffe.web.dto.output.SessionDto;

import java.util.UUID;

/**
 * @author Nidhal Dogga
 * @created 11/14/2020 2:33 PM
 * SoftKall™ All rights reserved.
 */


public interface SessionService {
  SessionDto createSession(CreateSessionDto request, UUID userId);
}
