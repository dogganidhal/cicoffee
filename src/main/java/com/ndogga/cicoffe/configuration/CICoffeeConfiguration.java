package com.ndogga.cicoffe.configuration;

import com.ndogga.cicoffe.configuration.properties.JwtConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Nidhal Dogga
 * @since 11/13/2020 10:50 PM
 * Prize & Fun™ All rights reserved.
 */


@Configuration
@EnableConfigurationProperties(
        JwtConfigurationProperties.class
)
public class CICoffeeConfiguration {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
