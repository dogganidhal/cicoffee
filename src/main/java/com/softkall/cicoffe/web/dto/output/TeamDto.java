package com.softkall.cicoffe.web.dto.output;

import lombok.Builder;
import lombok.Data;

import java.util.Collection;
import java.util.UUID;


/**
 * @author Nidhal Dogga
 * @since 11/13/2020 9:58 PM
 * SoftKall™ All rights reserved.
 */


@Data
@Builder
public class TeamDto {
  private UUID id;
  private String name;
  private Collection<MemberDto> members;
}
