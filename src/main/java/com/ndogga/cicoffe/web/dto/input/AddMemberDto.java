package com.ndogga.cicoffe.web.dto.input;

import lombok.Data;

import java.util.UUID;


/**
 * @author Nidhal Dogga
 * @since 11/13/2020 10:27 PM
 * Prize & Fun™ All rights reserved.
 */


@Data
public class AddMemberDto {
  private UUID memberId;
  private UUID teamId;
}
