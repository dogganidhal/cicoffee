package com.softkall.cicoffe.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Nidhal Dogga
 * @created 11/13/2020 11:24 PM
 * SoftKall™ All rights reserved.
 */


@Data
@ConfigurationProperties("cicoffee.web")
public class WebConfigurationProperties {
  private String protocol;
  private String host;
  private Integer port;
}
