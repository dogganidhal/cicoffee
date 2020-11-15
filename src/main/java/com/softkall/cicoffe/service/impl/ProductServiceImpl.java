package com.softkall.cicoffe.service.impl;


import com.softkall.cicoffe.model.repository.ProductRepository;
import com.softkall.cicoffe.service.ProductService;
import com.softkall.cicoffe.web.dto.output.ProductDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;


/**
 * @author nidhaldogga
 * @created 15/11/2020 16:39
 * SoftKall™ All rights reserved.
 */


@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  @Override
  public Collection<ProductDto> getAllProducts() {
    return productRepository.findAll().stream()
            .map(ProductDto::from)
            .collect(Collectors.toList());
  }

}
