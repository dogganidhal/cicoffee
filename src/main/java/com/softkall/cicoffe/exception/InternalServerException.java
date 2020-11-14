package com.softkall.cicoffe.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Nidhal Dogga
 * @created 11/14/2020 3:00 PM
 * SoftKall™ All rights reserved.
 */


public class InternalServerException extends ApiException {

  @Override
  public HttpStatus getStatus() {
    return HttpStatus.INTERNAL_SERVER_ERROR;
  }

  @Override
  public ErrorCode errorCode() {
    return ErrorCode.INTERNAL_SERVER_ERROR;
  }

}
