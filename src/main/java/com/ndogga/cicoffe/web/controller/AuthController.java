package com.ndogga.cicoffe.web.controller;


import com.ndogga.cicoffe.service.AuthService;
import com.ndogga.cicoffe.web.dto.input.LoginDto;
import com.ndogga.cicoffe.web.dto.input.RefreshDto;
import com.ndogga.cicoffe.web.dto.output.TokenDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


/**
 * @author Nidhal Dogga
 * @since 11/13/2020 10:59 PM
 * Prize & Fun™ All rights reserved.
 */


@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController extends AbstractController {

  private final AuthService authService;

  @PostMapping("/login")
  public Mono<TokenDto> login(@RequestBody LoginDto request) {
    return Mono.just(authService.login(request));
  }

  @PostMapping("/refresh")
  public Mono<TokenDto> refresh(@RequestBody RefreshDto request) {
    return Mono.just(authService.refresh(request.getToken()));
  }

}
