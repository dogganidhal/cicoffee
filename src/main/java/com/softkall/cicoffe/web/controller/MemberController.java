package com.softkall.cicoffe.web.controller;

import com.softkall.cicoffe.security.Authenticated;
import com.softkall.cicoffe.service.MemberService;
import com.softkall.cicoffe.web.dto.input.CreateMemberDto;
import com.softkall.cicoffe.web.dto.input.CreateMobileDeviceDto;
import com.softkall.cicoffe.web.dto.output.MemberDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Collection;


/**
 * @author Nidhal Dogga
 * @created 11/13/2020 7:42 PM
 * SoftKall™ All rights reserved.
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

  @Authenticated
  @GetMapping("/search")
  public Mono<Collection<MemberDto>> searchMembers(String query) {
    return Mono.just(memberService.searchMembers(query));
  }

  @Authenticated
  @PostMapping("/device")
  public Mono<Void> registerDevice(@RequestBody CreateMobileDeviceDto request, Authentication authentication) {
    return Mono.fromRunnable(() -> memberService.registerDevice(request, getMemberId(authentication)));
  }

  @Authenticated
  @DeleteMapping("/device/{identifier}")
  public Mono<Void> unregisterDevice(@PathVariable String identifier, Authentication authentication) {
    return Mono.fromRunnable(() -> memberService.unregisterDevice(identifier, getMemberId(authentication)));
  }

}
