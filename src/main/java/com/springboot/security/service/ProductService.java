package com.springboot.security.service;

import com.springboot.security.data.dto.ProductDto;
import com.springboot.security.data.dto.ProductResponseDto;

public interface ProductService {

    ProductResponseDto saveProduct(ProductDto productDto);
    ProductResponseDto getProduct(Long number);
    ProductResponseDto changeProductName(Long number, String name) throws Exception;
    void deleteProduct(Long number) throws Exception;

}
