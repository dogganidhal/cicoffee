package com.softkall.cicoffe.service;

import com.softkall.cicoffe.model.entity.Member;
import com.softkall.cicoffe.web.dto.input.LoginDto;
import com.softkall.cicoffe.web.dto.output.TokenDto;

import java.util.UUID;

/**
 * @author Nidhal Dogga
 * @created 11/13/2020 11:01 PM
 * SoftKall™ All rights reserved.
 */


public interface AuthService {
  UUID decodeJwt(String token);
  TokenDto login(LoginDto request);
  TokenDto refresh(String refreshToken);
  void forgotPassword(String email);
  void resetPassword(Member member, String password);
}
