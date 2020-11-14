package com.softkall.cicoffe.security;

import com.softkall.cicoffe.exception.InvalidCredentialsException;
import com.softkall.cicoffe.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.UUID;


/**
 * @author Nidhal Dogga
 * @created 11/14/2020 12:08 AM
 * SoftKall™ All rights reserved.
 */


@Component
@AllArgsConstructor
public class AuthenticationManager implements ReactiveAuthenticationManager {

  private final AuthService authService;

  @Override
  public Mono<Authentication> authenticate(Authentication authentication) {
    String token = authentication.getCredentials().toString();
    UUID memberId = authService.decodeJwt(token);
    if (memberId == null) {
      throw new InvalidCredentialsException();
    }
    Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
            memberId.toString(),
            null,
            Collections.emptyList()
    );;
    return Mono.just(authenticationToken);
  }

}
