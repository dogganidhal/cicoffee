package com.ndogga.cicoffe.service;

import com.ndogga.cicoffe.web.dto.input.CreateMemberDto;
import com.ndogga.cicoffe.web.dto.output.MemberDto;

import java.util.UUID;


/**
 * @author Nidhal Dogga
 * @since 11/13/2020 10:48 PM
 * Prize & Fun™ All rights reserved.
 */


public interface MemberService {
  MemberDto signUp(CreateMemberDto member);
  MemberDto me(UUID memberId);
}
