package com.softkall.cicoffe.model.entity;


import lombok.*;

import javax.persistence.*;
import java.util.UUID;

/**
 * @author Nidhal Dogga
 * @created 11/15/2020 12:39 AM
 * SoftKall™ All rights reserved.
 */


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "mobile_devices")
@EqualsAndHashCode(exclude = "member")
public class MobileDevice {
  @Id
  @GeneratedValue
  private UUID id;

  @Column(unique = true)
  private String identifier;

  @Column
  private String name;

  @ManyToOne
  private Member member;
}
