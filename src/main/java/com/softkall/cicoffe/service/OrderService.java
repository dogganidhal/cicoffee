package com.softkall.cicoffe.service;


import com.softkall.cicoffe.web.dto.input.CreateOrderDto;
import com.softkall.cicoffe.web.dto.output.OrderDto;

import java.util.Collection;
import java.util.UUID;

/**
 * @author Nidhal Dogga
 * @created 11/15/2020 2:53 AM
 * SoftKall™ All rights reserved.
 */


public interface OrderService {
  OrderDto order(UUID memberId, UUID sessionId, CreateOrderDto request);
  Collection<OrderDto> getOrders(UUID memberId, UUID sessionId);
}
