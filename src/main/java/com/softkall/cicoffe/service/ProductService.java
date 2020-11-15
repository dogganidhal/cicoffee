package com.softkall.cicoffe.service;


import com.softkall.cicoffe.web.dto.output.ProductDto;

import java.util.Collection;

/**
 * @author nidhaldogga
 * @created 15/11/2020 16:39
 * SoftKall™ All rights reserved.
 */


public interface ProductService {
  Collection<ProductDto> getAllProducts();
}
