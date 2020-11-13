package com.ndogga.cicoffe.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;


/**
 * @author Nidhal Dogga
 * @since 11/13/2020 9:43 PM
 * Prize & Fun™ All rights reserved.
 */


@Data
@Entity(name = "orders")
public class Order {
  @Id
  @GeneratedValue
  private UUID id;

  @Column
  private Integer quantity;

  @ManyToOne
  private Session session;

  @ManyToMany
  @JoinTable(
          name = "order_products",
          joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id")
  )
  private Collection<Product> products;
}
