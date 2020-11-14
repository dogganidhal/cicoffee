package com.softkall.cicoffe.configuration;

import com.softkall.cicoffe.security.AuthenticationManager;
import com.softkall.cicoffe.security.SecurityContextRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;


/**
 * @author Nidhal Dogga
 * @since 11/14/2020 12:17 AM
 * SoftKall™ All rights reserved.
 */


@Configuration
@AllArgsConstructor
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class WebSecurityConfiguration {

  private final AuthenticationManager authenticationManager;
  private final SecurityContextRepository securityContextRepository;

  @Bean
  public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
    return http
            .exceptionHandling()
            .authenticationEntryPoint((exchange, e) -> Mono.fromRunnable(() -> exchange
                    .getResponse()
                    .setStatusCode(HttpStatus.UNAUTHORIZED)
            ))
            .accessDeniedHandler((exchange, e) -> Mono.fromRunnable(() -> exchange
                    .getResponse()
                    .setStatusCode(HttpStatus.FORBIDDEN)
            ))
            .and()
            .csrf().disable()
            .formLogin().disable()
            .httpBasic().disable()
            .authenticationManager(authenticationManager)
            .securityContextRepository(securityContextRepository)
            .authorizeExchange()
            .anyExchange().permitAll()
            .and().build();
  }

}
