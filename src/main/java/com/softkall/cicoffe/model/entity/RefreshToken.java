package com.softkall.cicoffe.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


/**
 * @author Nidhal Dogga
 * @created 11/13/2020 11:08 PM
 * SoftKall™ All rights reserved.
 */


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "refresh_tokens")
public class RefreshToken {
  @Id
  private String token;

  @ManyToOne
  private Member member;
}
