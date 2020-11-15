package com.softkall.cicoffe.web.dto.output;

import com.softkall.cicoffe.model.entity.Product;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;


/**
 * @author Nidhal Dogga
 * @created 11/13/2020 10:10 PM
 * SoftKall™ All rights reserved.
 */


@Data
@Builder
public class ProductDto {

  private UUID id;
  private String name;
  private String photoUrl;

  public static ProductDto from(Product product) {
    return ProductDto.builder()
            .id(product.getId())
            .name(product.getName())
            .photoUrl(product.getPhotoUrl())
            .build();
  }

}
