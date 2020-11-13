package com.ndogga.cicoffe.web.dto.input;

import lombok.Data;

import javax.validation.constraints.NotBlank;


/**
 * @author Nidhal Dogga
 * @since 11/13/2020 10:12 PM
 * Prize & Fun™ All rights reserved.
 */


@Data
public class CreateTeamDto {
  @NotBlank
  private String name;
}
