package com.softkall.cicoffe.service.impl;

import com.softkall.cicoffe.exception.BadRequestException;
import com.softkall.cicoffe.exception.ForbiddenException;
import com.softkall.cicoffe.model.entity.Member;
import com.softkall.cicoffe.model.entity.Session;
import com.softkall.cicoffe.model.entity.SessionParticipant;
import com.softkall.cicoffe.model.entity.Team;
import com.softkall.cicoffe.model.repository.*;
import com.softkall.cicoffe.service.CommunicationManager;
import com.softkall.cicoffe.service.SessionService;
import com.softkall.cicoffe.web.dto.input.CreateSessionDto;
import com.softkall.cicoffe.web.dto.output.SessionDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import java.sql.Date;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


/**
 * @author Nidhal Dogga
 * @created 11/14/2020 2:34 PM
 * SoftKall™ All rights reserved.
 */


@Service
@AllArgsConstructor
public class SessionServiceImpl implements SessionService {

  private final SessionRepository sessionRepository;
  private final MemberRepository memberRepository;
  private final TeamRepository teamRepository;
  private final SessionParticipantRepository sessionParticipantRepository;
  private final OrderRepository orderRepository;
  private final CommunicationManager communicationManager;
  private final OrderItemRepository orderItemRepository;
  private final OneSignalNotificationService notificationService;

  @Override
  public Collection<SessionDto> mySessions(UUID memberId) {
    return sessionRepository
            .findAllByTeam_Members_Id(memberId).stream()
            .map(SessionDto::from)
            .collect(Collectors.toList());
  }

  @Override
  public SessionDto createSession(CreateSessionDto request, UUID memberId) {
    if (request.getEndDate().isBefore(request.getStartDate())) {
      throw new BadRequestException();
    }
    Team team = teamRepository.getById(request.getTeamId());
    Member author = memberRepository.getById(memberId);
    Session session = sessionRepository
            .save(Session.builder()
                    .author(author)
                    .team(team)
                    .startDate(Timestamp.from(request.getStartDate().toInstant(ZoneOffset.UTC)))
                    .endDate(Timestamp.from(request.getEndDate().toInstant(ZoneOffset.UTC)))
                    .build()
            );
    communicationManager.notifyTeamOfSession(session);
    return SessionDto.from(session);
  }

  @Override
  public SessionDto confirmParticipation(UUID sessionId, UUID memberId) {
    Session session = sessionRepository.getById(sessionId);
    Optional<Member> member = session.getTeam().getMembers().stream()
            .filter(m -> m.getId().equals(memberId))
            .findFirst();
    if (member.isEmpty()) {
      throw new ForbiddenException();
    }
    boolean alreadyParticipating = session.getParticipants().stream()
            .anyMatch(participant -> participant.getMember().getId().equals(memberId));
    if (!alreadyParticipating) {
      SessionParticipant participant = sessionParticipantRepository
              .save(SessionParticipant.builder()
                      .member(member.get())
                      .session(session)
                      .build()
              );
      session.getParticipants().add(participant);
      session = sessionRepository.save(session);
      communicationManager.notifyTeamOfParticipation(participant);
    }
    return SessionDto.from(session);
  }

  @Override
  @Transactional
  public SessionDto retractParticipation(UUID sessionId, UUID memberId) {
    Session session = sessionRepository.getById(sessionId);
    session.getParticipants()
            .removeIf(participant -> participant.getMember().getId().equals(memberId));
    session = sessionRepository.save(session);
    sessionParticipantRepository.deleteByMember_IdAndSession_Id(memberId, sessionId);


    orderRepository.deleteAll(session.getOrders().stream()
            .filter(order -> order.getMember().getId().equals(memberId))
            .collect(Collectors.toList())
    );
    return SessionDto.from(session);
  }

  @Override
  public SessionDto getById(UUID sessionId) {
        return SessionDto.from(sessionRepository.getById(sessionId));
    }

}
