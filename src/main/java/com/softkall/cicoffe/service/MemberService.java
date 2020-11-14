package com.softkall.cicoffe.service;

import com.softkall.cicoffe.web.dto.input.CreateMemberDto;
import com.softkall.cicoffe.web.dto.output.MemberDto;

import java.util.UUID;


/**
 * @author Nidhal Dogga
 * @since 11/13/2020 10:48 PM
 * SoftKall™ All rights reserved.
 */


public interface MemberService {
  MemberDto signUp(CreateMemberDto member);
  MemberDto me(UUID memberId);
}
