package com.softkall.cicoffe.model.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;


/**
 * @author Nidhal Dogga
 * @created 11/13/2020 9:43 PM
 * SoftKall™ All rights reserved.
 */


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "orders")
public class Order {
  @Id
  @GeneratedValue
  private UUID id;

  @ManyToOne
  private Session session;

  @ManyToOne
  private Member member;

  @Fetch(value = FetchMode.SELECT)
  @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(fetch = FetchType.EAGER, mappedBy = "order")
  private Set<OrderItem> items;
}
