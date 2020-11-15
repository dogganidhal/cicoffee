package com.softkall.cicoffe.model.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;


/**
 * @author Nidhal Dogga
 * @created 11/15/2020 12:18 AM
 * SoftKall™ All rights reserved.
 */


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "session_participants")
public class SessionParticipant {
  @Id
  @GeneratedValue
  private UUID id;

  @ManyToOne
  private Session session;

  @ManyToOne
  private Member member;
}
