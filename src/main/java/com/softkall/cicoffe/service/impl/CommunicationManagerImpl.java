package com.softkall.cicoffe.service.impl;

import com.softkall.cicoffe.model.entity.Session;
import com.softkall.cicoffe.model.entity.SessionParticipant;
import com.softkall.cicoffe.service.CommunicationManager;
import com.softkall.cicoffe.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;


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
    notificationService.sendNotification(
            session.getTeam().getMembers().stream()
                    .filter(member -> !member.getId().equals(session.getAuthor().getId()))
                    .collect(Collectors.toList()),
            "Ready for a coffee break ? ☕",
                    String.format(
                    "%s has invited you to have some coffee with team %s",
                    session.getAuthor().getFirstName(),
                    session.getTeam().getName().toUpperCase()
            ));
  }

  @Override
  public void notifyTeamOfParticipation(SessionParticipant participant) {

  }

}
