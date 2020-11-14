package com.softkall.cicoffe.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Nidhal Dogga
 * @created 11/14/2020 2:45 PM
 * SoftKall™ All rights reserved.
 */


public class ForbiddenException extends ApiException {

  @Override
  public HttpStatus getStatus() {
    return HttpStatus.UNAUTHORIZED;
  }

  @Override
  public ErrorCode errorCode() {
    return ErrorCode.FORBIDDEN;
  }

}
