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


/**
 * @author Nidhal Dogga
 * @since 11/13/2020 10:59 PM
 * Prize & Fun™ All rights reserved.
 */


@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

  private final AuthService authService;

  @PostMapping
  public TokenDto login(@RequestBody LoginDto request) {
    return authService.login(request);
  }

  @PostMapping("/refresh")
  public TokenDto refresh(@RequestBody RefreshDto request) {
    return authService.refresh(request.getToken());
  }

}
