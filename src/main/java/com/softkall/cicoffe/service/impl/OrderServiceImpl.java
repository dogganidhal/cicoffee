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

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;
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
  @Transactional
  public OrderDto order(UUID memberId, UUID sessionId, CreateOrderDto request) {
    Session session = sessionRepository.getById(sessionId);
    if (session.getEndDate().before(Timestamp.from(Instant.now()))) {
      throw new BadRequestException();
    }
    boolean memberInTeam = session.getTeam().getMembers().stream()
            .anyMatch(member -> member.getId().equals(memberId));
    if (!memberInTeam) {
      throw new ForbiddenException();
    }
    Member member = memberRepository.getById(memberId);
    Order order = Objects.requireNonNullElseGet(
            orderRepository.findByMemberIdAndSessionId(memberId, sessionId),
            () -> orderRepository.save(Order.builder()
                    .items(Collections.emptySet())
                    .member(member)
                    .session(session)
                    .build()
            )
    );
    orderItemRepository.removeAllByOrderId(order.getId());
    Set<OrderItem> items = new HashSet<>(orderItemRepository.saveAll(request.getItems().stream()
            .map(itemDto -> OrderItem.builder()
                    .product(productRepository.getById(itemDto.getProductId()))
                    .order(order)
                    .quantity(itemDto.getQuantity())
                    .build()
            )
            .collect(Collectors.toList())
    ));
    order.setItems(items);
    return OrderDto.from(orderRepository.save(order));
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
