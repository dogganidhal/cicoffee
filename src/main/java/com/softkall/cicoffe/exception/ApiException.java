package com.softkall.cicoffe.exception;

import org.springframework.http.HttpStatus;


/**
 * @author Nidhal Dogga
 * @created 11/14/2020 2:39 PM
 * SoftKall™ All rights reserved.
 */


public abstract class ApiException extends RuntimeException {

  public abstract HttpStatus getStatus();
  public abstract ErrorCode errorCode();

  public enum ErrorCode {
    NOT_FOUND,
    AUTHENTICATION_REQUIRED,
    FORBIDDEN,
    INVALID_CREDENTIALS,
    INTERNAL_SERVER_ERROR
  }

}
