package com.softkall.cicoffe.web.dto.input;

import lombok.Data;

import javax.validation.constraints.NotBlank;


/**
 * @author Nidhal Dogga
 * @created 11/13/2020 10:12 PM
 * SoftKall™ All rights reserved.
 */


@Data
public class CreateTeamDto {
  @NotBlank
  private String name;

}
