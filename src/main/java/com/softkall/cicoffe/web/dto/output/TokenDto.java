package com.softkall.cicoffe.web.dto.output;

import lombok.Builder;
import lombok.Data;


/**
 * @author Nidhal Dogga
 * @since 11/13/2020 11:01 PM
 * SoftKall™ All rights reserved.
 */


@Data
@Builder
public class TokenDto {
  private String accessToken;
  private String refreshToken;
  private Long expiresIn;
  private String type;
}
