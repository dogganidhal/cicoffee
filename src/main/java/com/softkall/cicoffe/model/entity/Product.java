package com.softkall.cicoffe.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;


/**
 * @author Nidhal Dogga
 * @created 11/13/2020 9:45 PM
 * SoftKall™ All rights reserved.
 */


@Data
@Entity(name = "products")
public class Product {
  @Id
  @GeneratedValue
  private UUID id;

  @Column
  private String name;

  @Column(name = "photo_url")
  private String photoUrl;
}
