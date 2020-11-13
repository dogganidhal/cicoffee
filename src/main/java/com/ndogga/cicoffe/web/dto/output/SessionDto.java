package com.ndogga.cicoffe.web.dto.output;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;


/**
 * @author Nidhal Dogga
 * @since 11/13/2020 10:07 PM
 * Prize & Fun™ All rights reserved.
 */


@Data
@Builder
public class SessionDto {
  private UUID id;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private TeamDto team;
  private MemberDto author;
  private MemberDto payer;
  private Collection<OrderDto> orders;
}
