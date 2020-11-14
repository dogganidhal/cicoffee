package com.softkall.cicoffe.web.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author Nidhal Dogga
 * @created 11/14/2020 8:48 PM
 * SoftKall™ All rights reserved.
 */


@Slf4j
@Component
@AllArgsConstructor
public class DataBufferWriter {

  private final ObjectMapper objectMapper;

  public <T> Mono<Void> write(ServerHttpResponse httpResponse, T object) {
    httpResponse.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    return httpResponse
            .writeWith(Mono.fromSupplier(() -> {
              DataBufferFactory bufferFactory = httpResponse.bufferFactory();
              try {
                return bufferFactory.wrap(objectMapper.writeValueAsBytes(object));
              } catch (Exception ex) {
                log.warn("Error writing response", ex);
                return bufferFactory.wrap(new byte[0]);
              }
            }));
  }

}
