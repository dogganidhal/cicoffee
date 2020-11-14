package com.softkall.cicoffe.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;


/**
 * @author Nidhal Dogga
 * @since 11/13/2020 9:39 PM
 * SoftKall™ All rights reserved.
 */


@Data
@Entity(name = "sessions")
public class Session {
  @Id
  @GeneratedValue
  private UUID id;

  @Column(name = "start_date")
  private LocalDateTime startDate;

  @Column(name = "end_date")
  private LocalDateTime endDate;

  @ManyToOne
  private Team team;

  @ManyToOne
  private Member author;

  @ManyToOne
  private Member payer;

  @OneToMany
  private Collection<Order> orders;
}
