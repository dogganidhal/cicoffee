package com.softkall.cicoffe.model.repository;

import com.softkall.cicoffe.model.entity.Order;

import java.util.Collection;
import java.util.UUID;

/**
 * @author Nidhal Dogga
 * @created 11/15/2020 3:12 AM
 * SoftKall™ All rights reserved.
 */


public interface OrderRepository extends AbstractRepository<Order, UUID> {
  Collection<Order> findAllBySessionId(UUID sessionId);
}
