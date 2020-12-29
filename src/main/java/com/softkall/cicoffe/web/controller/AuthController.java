package com.softkall.cicoffe.web.controller;


import com.softkall.cicoffe.service.AuthService;
import com.softkall.cicoffe.web.dto.input.LoginDto;
import com.softkall.cicoffe.web.dto.input.RefreshDto;
import com.softkall.cicoffe.web.dto.output.TokenDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


/**
 * @author Nidhal Dogga
 * @created 11/13/2020 10:59 PM
 * SoftKall™ All rights reserved.
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

  @GetMapping("/reset-password/{email}")
  public Mono<Void> resetPassword(@PathVariable String email) {
    return Mono.fromRunnable(() -> authService.forgotPassword(email));
  }
}
