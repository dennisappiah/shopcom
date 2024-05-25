package com.shopping_mart.shoppy.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ModifyCartRequest {

    @NotEmpty(message = "productId is required")
    private Integer productId;

    @NotEmpty(message = "username is required")
    private String username;

    @NotEmpty(message = "quantity is required")
    private int quantity;
}
