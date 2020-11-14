package com.softkall.cicoffe.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Nidhal Dogga
 * @created 11/14/2020 2:43 PM
 * SoftKall™ All rights reserved.
 */


public class InvalidCredentialsException extends ApiException {

  @Override
  public HttpStatus getStatus() {
    return HttpStatus.UNAUTHORIZED;
  }

  @Override
  public ErrorCode errorCode() {
    return ErrorCode.INVALID_CREDENTIALS;
  }

}
