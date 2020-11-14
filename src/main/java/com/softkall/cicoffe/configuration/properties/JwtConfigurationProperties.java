package com.softkall.cicoffe.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Nidhal Dogga
 * @created 11/13/2020 11:24 PM
 * SoftKall™ All rights reserved.
 */


@Data
@ConfigurationProperties("cicoffee.auth.jwt")
public class JwtConfigurationProperties {
  private Long expiresIn;
  private Long refreshTokenExpiresIn;
  private String signingKey;
}
