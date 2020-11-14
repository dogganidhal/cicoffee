package com.softkall.cicoffe.web.dto.output;

import com.softkall.cicoffe.exception.ApiException;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author Nidhal Dogga
 * @created 11/14/2020 2:56 PM
 * SoftKall™ All rights reserved.
 */


@Data
@Builder
public class ExceptionDto {
  private HttpStatus status;
  private ApiException.ErrorCode errorCode;

  public static ExceptionDto fromApiException(ApiException exception) {
    return ExceptionDto.builder()
            .errorCode(exception.errorCode())
            .status(exception.getStatus())
            .build();
  }
}
