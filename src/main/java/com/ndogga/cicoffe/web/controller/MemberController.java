package com.ndogga.cicoffe.web.controller;

import com.ndogga.cicoffe.security.Authenticated;
import com.ndogga.cicoffe.service.MemberService;
import com.ndogga.cicoffe.web.dto.input.CreateMemberDto;
import com.ndogga.cicoffe.web.dto.output.MemberDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


/**
 * @author Nidhal Dogga
 * @since 11/13/2020 7:42 PM
 * Prize & Fun™ All rights reserved.
 */


@RestController
@AllArgsConstructor
@RequestMapping("/api/member")
public class MemberController extends AbstractController {

  private final MemberService memberService;

  @PostMapping
  public Mono<MemberDto> signUp(@RequestBody CreateMemberDto member) {
    return Mono.just(memberService.signUp(member));
  }

  @GetMapping
  @Authenticated
  public Mono<MemberDto> me(Authentication authentication) {
    return Mono.just(memberService.me(getMemberId(authentication)));
  }

}
