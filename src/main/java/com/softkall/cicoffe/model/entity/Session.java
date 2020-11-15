package com.softkall.cicoffe.model.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;


/**
 * @author Nidhal Dogga
 * @created 11/13/2020 9:39 PM
 * SoftKall™ All rights reserved.
 */


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

  @Fetch(value = FetchMode.SELECT)
  @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(fetch = FetchType.EAGER, mappedBy = "session")
  private Set<Order> orders;

  @Fetch(value = FetchMode.SELECT)
  @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(fetch = FetchType.EAGER, mappedBy = "session")
  private Set<SessionParticipant> participants;
}
