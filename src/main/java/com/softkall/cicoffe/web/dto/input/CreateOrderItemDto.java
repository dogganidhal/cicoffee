package com.softkall.cicoffe.web.dto.input;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * @author Nidhal Dogga
 * @created 11/15/2020 3:07 AM
 * SoftKall™ All rights reserved.
 */


@Data
public class CreateOrderItemDto {
  @NotNull
  private UUID productId;
  @NotNull
  private Integer quantity;
}
