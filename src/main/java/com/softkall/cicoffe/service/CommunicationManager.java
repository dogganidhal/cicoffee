package com.softkall.cicoffe.service;

import com.softkall.cicoffe.model.entity.Session;
import com.softkall.cicoffe.model.entity.SessionParticipant;


/**
 * @author Nidhal Dogga
 * @created 11/14/2020 11:58 PM
 * SoftKall™ All rights reserved.
 */


public interface CommunicationManager {
  void notifyTeamOfSession(Session session);
  void notifyTeamOfParticipation(SessionParticipant participant);
}
