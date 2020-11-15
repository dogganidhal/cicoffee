package com.softkall.cicoffe.web.controller;


import com.softkall.cicoffe.security.Authenticated;
import com.softkall.cicoffe.service.OrderService;
import com.softkall.cicoffe.service.SessionService;
import com.softkall.cicoffe.web.dto.input.CreateOrderDto;
import com.softkall.cicoffe.web.dto.input.CreateSessionDto;
import com.softkall.cicoffe.web.dto.output.OrderDto;
import com.softkall.cicoffe.web.dto.output.SessionDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.UUID;

/**
 * @author Nidhal Dogga
 * @created 11/15/2020 12:33 AM
 * SoftKall™ All rights reserved.
 */


@RestController
@AllArgsConstructor
@RequestMapping("/api/session")
public class SessionController extends AbstractController {

  private final SessionService sessionService;
  private final OrderService orderService;

  @GetMapping
  @Authenticated
  public Mono<Collection<SessionDto>> mySessions(Authentication authentication) {
    return Mono.just(sessionService.mySessions(getMemberId(authentication)));
  }

  @PostMapping
  @Authenticated
  public Mono<SessionDto> createSession(
          @RequestBody CreateSessionDto request,
          Authentication authentication
  ) {
    return Mono.just(sessionService.createSession(request, getMemberId(authentication)));
  }

  @Authenticated
  @PostMapping("/{sessionId}/confirm-participation")
  public Mono<SessionDto> confirmSessionParticipation(
          @PathVariable UUID sessionId,
          Authentication authentication
  ) {
    return Mono.just(sessionService.confirmParticipation(sessionId, getMemberId(authentication)));
  }

  @Authenticated
  @PostMapping("/{sessionId}/retract-participation")
  public Mono<SessionDto> retractSessionParticipation(
          @PathVariable UUID sessionId,
          Authentication authentication
  ) {
    return Mono.just(sessionService.retractParticipation(sessionId, getMemberId(authentication)));
  }

  @Authenticated
  @PostMapping("/{sessionId}/order")
  public Mono<OrderDto> order(
          @PathVariable UUID sessionId,
          @RequestBody CreateOrderDto request,
          Authentication authentication
  ) {
    return Mono.just(orderService.order(getMemberId(authentication), sessionId, request));
  }

  @Authenticated
  @GetMapping("/{sessionId}/order")
  public Mono<Collection<OrderDto>> order(
          @PathVariable UUID sessionId,
          Authentication authentication
  ) {
    return Mono.just(orderService.getOrders(getMemberId(authentication), sessionId));
  }

}
