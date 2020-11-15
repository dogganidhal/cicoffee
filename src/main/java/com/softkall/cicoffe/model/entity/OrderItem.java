package com.softkall.cicoffe.model.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;


/**
 * @author Nidhal Dogga
 * @created 11/15/2020 2:56 AM
 * SoftKall™ All rights reserved.
 */


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "order_items")
public class OrderItem {
  @Id
  @GeneratedValue
  private UUID id;

  @Column
  private Integer quantity;

  @ManyToOne
  private Order order;

  @ManyToOne
  private Product product;
}
