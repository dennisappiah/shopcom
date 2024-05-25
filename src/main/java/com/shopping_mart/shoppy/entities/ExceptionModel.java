package com.shopping_mart.shoppy.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Builder
public class ExceptionModel {

    private String message;

    private HttpStatus httpStatus;
}
