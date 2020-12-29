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
 * @created 11/13/2020 6:57 PM
 * SoftKall™ All rights reserved.
 */


@Data
@With
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "members")
public class Member {
  @Id
  @GeneratedValue
  private UUID id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(unique = true)
  private String email;

  @Column(name = "password_hash")
  private String passwordHash;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
          name = "team_members",
          joinColumns = @JoinColumn(name = "team_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "member_id", referencedColumnName = "id")
  )
  private Collection<Team> teams;

  @Fetch(value = FetchMode.SELECT)
  @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(fetch = FetchType.EAGER, mappedBy = "member")
  private Set<MobileDevice> mobileDevices;
}
