package com.softkall.cicoffe.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;


/**
 * @author Nidhal Dogga
 * @since 11/13/2020 6:53 PM
 * SoftKall™ All rights reserved.
 */


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "teams")
public class Team {
  @Id
  @GeneratedValue
  private UUID id;

  @Column
  private String name;

  @ManyToOne
  private Member creator;

  @ManyToMany(mappedBy = "teams", fetch = FetchType.EAGER)
  private Collection<Member> members;
}
