package com.softkall.cicoffe.web.dto.input;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * @author Nidhal Dogga
 * @created 11/15/2020 2:55 AM
 * SoftKall™ All rights reserved.
 */


@Data
public class CreateOrderDto {
  @NotNull
  @NotEmpty
  private Collection<CreateOrderItemDto> items;
}
