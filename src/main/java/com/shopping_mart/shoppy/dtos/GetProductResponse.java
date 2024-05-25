package com.shopping_mart.shoppy.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetProductResponse {

    private Integer id;

    private String name;

    private BigDecimal price;

    private String description;
}
