package com.softkall.cicoffe.web.controller;

import com.softkall.cicoffe.service.ProductService;
import com.softkall.cicoffe.web.dto.output.ProductDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Collection;


/**
 * @author Nidhal Dogga
 * @created 11/13/2020 10:59 PM
 * SoftKall™ All rights reserved.
 */


@RestController
@AllArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

  private final ProductService productService;

  @GetMapping
  public Mono<Collection<ProductDto>> getProducts() {
    return Mono.just(productService.getAllProducts());
  }

}
