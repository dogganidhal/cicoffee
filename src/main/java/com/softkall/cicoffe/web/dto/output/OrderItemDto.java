package com.softkall.cicoffe.web.dto.output;

import com.softkall.cicoffe.model.entity.OrderItem;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

/**
 * @author Nidhal Dogga
 * @created 11/15/2020 3:00 AM
 * SoftKall™ All rights reserved.
 */


@Data
@Builder
public class OrderItemDto {

  private UUID id;
  private Integer quantity;
  private ProductDto product;

  public static OrderItemDto from(OrderItem item) {
    return OrderItemDto.builder()
            .id(item.getId())
            .product(ProductDto.from(item.getProduct()))
            .quantity(item.getQuantity())
            .build();
  }

}
