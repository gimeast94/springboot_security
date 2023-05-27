package com.springboot.security.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductDto {
    private String name;
    private int price;
    private int stock;
}
