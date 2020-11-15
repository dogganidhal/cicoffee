package com.softkall.cicoffe.exception;


import org.springframework.http.HttpStatus;


/**
 * @author Nidhal Dogga
 * @created 11/15/2020 12:59 AM
 * SoftKall™ All rights reserved.
 */


public class BadRequestException extends ApiException {

  @Override
  public HttpStatus getStatus() {
    return HttpStatus.BAD_REQUEST;
  }

  @Override
  public ErrorCode errorCode() {
    return ErrorCode.BAD_REQUEST;
  }

}
