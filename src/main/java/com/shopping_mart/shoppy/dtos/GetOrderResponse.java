package com.shopping_mart.shoppy.dtos;

import com.shopping_mart.shoppy.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetOrderResponse {
    private Integer id;

    private Integer user_id;

    private List<Product> products;

    private BigDecimal total;

}
