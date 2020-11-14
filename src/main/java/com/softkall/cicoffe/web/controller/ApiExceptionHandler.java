package com.softkall.cicoffe.web.controller;

import com.softkall.cicoffe.exception.ApiException;
import com.softkall.cicoffe.exception.InternalServerException;
import com.softkall.cicoffe.web.dto.output.ExceptionDto;
import com.softkall.cicoffe.web.utils.DataBufferWriter;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


/**
 * @author Nidhal Dogga
 * @created 11/14/2020 2:47 PM
 * SoftKall™ All rights reserved.
 */


@Component
@AllArgsConstructor
public class ApiExceptionHandler implements ErrorWebExceptionHandler {

  private final DataBufferWriter bufferWriter;

  @Override
  public Mono<Void> handle(ServerWebExchange exchange, Throwable throwable) {
    ApiException apiException = new InternalServerException();
    if (throwable instanceof ApiException) {
      apiException = (ApiException) throwable;
    }
    exchange.getResponse().setStatusCode(apiException.getStatus());
    return bufferWriter.write(exchange.getResponse(), ExceptionDto.fromApiException(apiException));
  }

}
