package com.springboot.security.data.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductResponseDto {

    private Long number;
    private String name;
    private Integer price;
    private Integer stock;

}
