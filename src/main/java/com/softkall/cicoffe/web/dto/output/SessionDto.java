package com.softkall.cicoffe.web.dto.output;

import com.softkall.cicoffe.model.entity.Session;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;
import java.util.stream.Collectors;


/**
 * @author Nidhal Dogga
 * @created 11/13/2020 10:07 PM
 * SoftKall™ All rights reserved.
 */


@Data
@Builder
public class SessionDto {

  private UUID id;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private TeamDto team;
  private MemberDto author;
  private Collection<OrderDto> orders;
  private Collection<MemberDto> participants;

  public static SessionDto from(Session session) {
    return SessionDto.builder()
            .id(session.getId())
            .author(MemberDto.from(session.getAuthor()))
            .team(TeamDto.from(session.getTeam()))
            .orders(session.getOrders() != null ?
                    session.getOrders().stream()
                            .map(OrderDto::from)
                            .collect(Collectors.toList()) :
                    Collections.emptyList()
            )
            .participants(session.getParticipants() != null ?
                    session.getParticipants().stream()
                            .map(participant -> MemberDto.from(participant.getMember()))
                            .collect(Collectors.toList()) :
                    Collections.emptyList()
            )
            .startDate(session.getStartDate().toLocalDateTime())
            .endDate(session.getEndDate().toLocalDateTime())
            .build();
  }

}
