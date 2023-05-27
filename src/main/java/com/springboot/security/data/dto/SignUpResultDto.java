package com.springboot.security.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SignUpResultDto {

    private boolean success;

    private int code;

    private String msg;
}
