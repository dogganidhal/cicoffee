package com.softkall.cicoffe.exception;

import org.springframework.http.HttpStatus;


/**
 * @author Nidhal Dogga
 * @created 11/14/2020 2:41 PM
 * SoftKall™ All rights reserved.
 */


public class UserExistsException extends ApiException {

  @Override
  public HttpStatus getStatus() {
    return HttpStatus.BAD_REQUEST;
  }

  @Override
  public ErrorCode errorCode() {
    return ErrorCode.USER_EXISTS;
  }

}
