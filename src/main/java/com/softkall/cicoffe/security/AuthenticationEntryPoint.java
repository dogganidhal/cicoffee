package com.softkall.cicoffe.security;

import com.softkall.cicoffe.exception.ApiException;
import com.softkall.cicoffe.exception.InternalServerException;
import com.softkall.cicoffe.exception.InvalidCredentialsException;
import com.softkall.cicoffe.web.dto.output.ExceptionDto;
import com.softkall.cicoffe.web.utils.DataBufferWriter;
import lombok.AllArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author Nidhal Dogga
 * @created 11/14/2020 8:44 PM
 * SoftKall™ All rights reserved.
 */


@Component
@AllArgsConstructor
public class AuthenticationEntryPoint implements ServerAuthenticationEntryPoint {

  private final DataBufferWriter bufferWriter;

  @Override
  public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException throwable) {
    ApiException apiException = new InvalidCredentialsException();
    exchange.getResponse().setStatusCode(apiException.getStatus());
    return bufferWriter.write(exchange.getResponse(), ExceptionDto.fromApiException(apiException));
  }

}
