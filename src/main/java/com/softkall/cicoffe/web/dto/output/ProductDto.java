package com.softkall.cicoffe.web.dto.output;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;


/**
 * @author Nidhal Dogga
 * @since 11/13/2020 10:10 PM
 * SoftKall™ All rights reserved.
 */


@Data
@Builder
public class ProductDto {
  private UUID id;
  private String name;
  private String photoUrl;
}
