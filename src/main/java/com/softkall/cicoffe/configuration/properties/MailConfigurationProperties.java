package com.softkall.cicoffe.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * @author Nidhal Dogga
 * @created 12/12/2020 03:34
 * SoftKall™ All rights reserved.
 */


@Data
@ConfigurationProperties("cicoffee.mail")
public class MailConfigurationProperties {
  private String from;
}
