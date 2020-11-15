package com.softkall.cicoffe.web.dto.input;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author nidhaldogga
 * @created 15/11/2020 16:45
 * SoftKall™ All rights reserved.
 */


@Data
public class CreateMobileDeviceDto {

  @NotBlank
  @NotNull
  private String identifier;
  private String name;

}
