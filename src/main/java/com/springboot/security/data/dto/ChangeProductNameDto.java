package com.springboot.security.data.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class ChangeProductNameDto {
    private Long number;
    private String name;
    private LocalDateTime updatedAt;
}
