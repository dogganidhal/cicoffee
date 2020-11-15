package com.softkall.cicoffe.web.dto.output;

import com.softkall.cicoffe.model.entity.Order;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;


/**
 * @author Nidhal Dogga
 * @created 11/13/2020 10:06 PM
 * SoftKall™ All rights reserved.
 */


@Data
@Builder
public class OrderDto {

  private UUID id;
  private MemberDto member;
  private SessionDto session;
  private Collection<OrderItemDto> items;

  public static OrderDto from(Order order) {
    return OrderDto.builder()
            .id(order.getId())
            .member(MemberDto.from(order.getMember()))
            .items(order.getItems().stream()
                    .map(OrderItemDto::from)
                    .collect(Collectors.toList())
            )
            .build();
  }

}
