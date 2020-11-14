package com.ndogga.cicoffe.service;

import com.ndogga.cicoffe.web.dto.input.LoginDto;
import com.ndogga.cicoffe.web.dto.output.TokenDto;

import java.util.UUID;

/**
 * @author Nidhal Dogga
 * @since 11/13/2020 11:01 PM
 * Prize & Fun™ All rights reserved.
 */


public interface AuthService {
  UUID decodeJwt(String token);
  TokenDto login(LoginDto request);
  TokenDto refresh(String refreshToken);
}
