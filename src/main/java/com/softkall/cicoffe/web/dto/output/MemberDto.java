package com.softkall.cicoffe.web.dto.output;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

/**
 * @author Nidhal Dogga
 * @since 11/13/2020 10:03 PM
 * SoftKall™ All rights reserved.
 */



@Data
@Builder
public class MemberDto {
  private UUID id;
  private String firstName;
  private String lastName;
  private String email;
}
