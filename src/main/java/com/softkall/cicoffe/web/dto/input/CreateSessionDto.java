package com.softkall.cicoffe.web.dto.input;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;


/**
 * @author Nidhal Dogga
 * @created 11/13/2020 10:12 PM
 * SoftKall™ All rights reserved.
 */


@Data
@Builder
public class CreateSessionDto {
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private UUID teamId;
}
