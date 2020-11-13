package com.ndogga.cicoffe.web.controller;

import com.ndogga.cicoffe.service.MemberService;
import com.ndogga.cicoffe.web.dto.input.CreateMemberDto;
import com.ndogga.cicoffe.web.dto.output.MemberDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


/**
 * @author Nidhal Dogga
 * @since 11/13/2020 7:42 PM
 * Prize & Fun™ All rights reserved.
 */


@RestController
@AllArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

  private final MemberService memberService;

  @PostMapping
  public MemberDto signUp(@RequestBody CreateMemberDto member) {
    return memberService.signUp(member);
  }

  @GetMapping
  public MemberDto me() {
    // TODO: Grab the user id from auth
    return memberService.me(UUID.randomUUID());
  }

}
