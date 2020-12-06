package com.softkall.cicoffe.service;

import com.softkall.cicoffe.web.dto.input.CreateMemberDto;
import com.softkall.cicoffe.web.dto.input.CreateMobileDeviceDto;
import com.softkall.cicoffe.web.dto.output.MemberDto;

import java.util.List;
import java.util.UUID;


/**
 * @author Nidhal Dogga
 * @created 11/13/2020 10:48 PM
 * SoftKall™ All rights reserved.
 */


public interface MemberService {
  MemberDto signUp(CreateMemberDto member);
  MemberDto me(UUID memberId);
  void registerDevice(CreateMobileDeviceDto request, UUID memberId);
  void unregisterDevice(String identifier, UUID memberId);
  List<MemberDto> searchMembers(String query);
}
