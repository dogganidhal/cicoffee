package com.ndogga.cicoffe.web.controller;

import org.springframework.security.core.Authentication;

import java.util.UUID;

/**
 * @author Nidhal Dogga
 * @since 11/14/2020 1:00 AM
 * Prize & Fun™ All rights reserved.
 */


public abstract class AbstractController {

  protected UUID getMemberId(Authentication authentication) {
    return UUID.fromString(authentication.getPrincipal().toString());
  }

}
