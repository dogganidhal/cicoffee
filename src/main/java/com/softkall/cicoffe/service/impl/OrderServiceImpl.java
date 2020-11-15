package com.softkall.cicoffe.service.impl;


import com.softkall.cicoffe.exception.BadRequestException;
import com.softkall.cicoffe.exception.ForbiddenException;
import com.softkall.cicoffe.model.entity.*;
import com.softkall.cicoffe.model.repository.*;
import com.softkall.cicoffe.service.OrderService;
import com.softkall.cicoffe.web.dto.input.CreateOrderDto;
import com.softkall.cicoffe.web.dto.output.OrderDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Nidhal Dogga
 * @created 11/15/2020 2:53 AM
 * SoftKall™ All rights reserved.
 */


@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final MemberRepository memberRepository;
  private final SessionRepository sessionRepository;
  private final OrderRepository orderRepository;
  private final ProductRepository productRepository;
  private final OrderItemRepository orderItemRepository;

  @Override
  public OrderDto order(UUID memberId, UUID sessionId, CreateOrderDto request) {
    Session session = sessionRepository.getById(sessionId);
    if (session.getEndDate().isAfter(LocalDateTime.now())) {
      throw new BadRequestException();
    }
    boolean memberInTeam = session.getTeam().getMembers().stream()
            .anyMatch(member -> member.getId().equals(memberId));
    if (!memberInTeam) {
      throw new ForbiddenException();
    }
    Member member = memberRepository.getById(memberId);
    Collection<OrderItem> items = request.getItems().stream()
            .map(itemDto -> {
              Product product = productRepository.getById(itemDto.getProductId());
              return orderItemRepository.save(OrderItem.builder()
                      .product(product)
                      .quantity(itemDto.getQuantity())
                      .build()
              );
            })
            .collect(Collectors.toList());
    Order order = orderRepository.save(Order.builder()
            .items(items)
            .member(member)
            .session(session)
            .build()
    );
    return OrderDto.from(order);
  }

  @Override
  public Collection<OrderDto> getOrders(UUID memberId, UUID sessionId) {
    Session session = sessionRepository.getById(sessionId);
    boolean memberInTeam = session.getTeam().getMembers().stream()
            .anyMatch(member -> member.getId().equals(memberId));
    if (!memberInTeam) {
      throw new ForbiddenException();
    }
    return orderRepository.findAllBySessionId(sessionId).stream()
            .map(OrderDto::from)
            .collect(Collectors.toList());
  }

}
