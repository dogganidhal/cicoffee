package com.softkall.cicoffe.web.dto.input;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @author Nidhal Dogga
 * @created 11/13/2020 11:00 PM
 * SoftKall™ All rights reserved.
 */


@Data
public class LoginDto {
  @Email
  private String email;
  @NotBlank
  @Min(6)
  private String password;
}
