package com.ndogga.cicoffe.service;

import com.ndogga.cicoffe.web.dto.input.LoginDto;
import com.ndogga.cicoffe.web.dto.output.TokenDto;

/**
 * @author Nidhal Dogga
 * @since 11/13/2020 11:01 PM
 * Prize & Fun™ All rights reserved.
 */


public interface AuthService {
  TokenDto login(LoginDto request);
  TokenDto refresh(String refreshToken);
}
