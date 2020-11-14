package com.softkall.cicoffe.web.dto.output;

import lombok.Builder;
import lombok.Data;

import java.util.Collection;
import java.util.UUID;

/**
 * @author Nidhal Dogga
 * @created 11/13/2020 10:06 PM
 * SoftKall™ All rights reserved.
 */


@Data
@Builder
public class OrderDto {
  private UUID id;
  private Integer quantity;
  private Object session;
  private Collection<ProductDto> products;
}
