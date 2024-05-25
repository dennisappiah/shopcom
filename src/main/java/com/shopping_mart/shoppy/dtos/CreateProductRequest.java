package com.shopping_mart.shoppy.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class CreateProductRequest {

    @NotEmpty(message = "product name is required")
    private String name;

    @NotNull(message = "price is required")
    private BigDecimal price;

    @NotEmpty(message = "description is required")
    private String description;
}
