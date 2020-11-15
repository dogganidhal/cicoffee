package com.softkall.cicoffe.service.impl;

import com.softkall.cicoffe.model.entity.Session;
import com.softkall.cicoffe.model.entity.SessionParticipant;
import com.softkall.cicoffe.service.CommunicationManager;
import com.softkall.cicoffe.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * @author Nidhal Dogga
 * @created 11/14/2020 11:59 PM
 * SoftKall™ All rights reserved.
 */


@Service
@AllArgsConstructor
public class CommunicationManagerImpl implements CommunicationManager {

  private final NotificationService notificationService;

  @Override
  public void notifyTeamOfSession(Session session) {

  }

  @Override
  public void notifyTeamOfParticipation(SessionParticipant participant) {

  }

}
