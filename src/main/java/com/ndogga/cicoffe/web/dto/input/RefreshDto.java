package com.ndogga.cicoffe.web.dto.input;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Nidhal Dogga
 * @since 11/13/2020 11:14 PM
 * Prize & Fun™ All rights reserved.
 */


@Data
public class RefreshDto {
  @NotBlank
  private String token;
}
