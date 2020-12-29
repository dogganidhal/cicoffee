package com.softkall.cicoffe.configuration;

import com.softkall.cicoffe.configuration.properties.JwtConfigurationProperties;
import com.softkall.cicoffe.configuration.properties.MailConfigurationProperties;
import com.softkall.cicoffe.configuration.properties.WebConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Nidhal Dogga
 * @created 11/13/2020 10:50 PM
 * SoftKall™ All rights reserved.
 */


@Configuration
@EnableConfigurationProperties({
        JwtConfigurationProperties.class,
        MailConfigurationProperties.class,
        WebConfigurationProperties.class
})
public class CICoffeeConfiguration {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
