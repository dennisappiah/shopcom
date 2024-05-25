package com.shopping_mart.shoppy.services;

import com.shopping_mart.shoppy.dtos.GetOrderResponse;
import com.shopping_mart.shoppy.entities.Order;

import java.util.List;

public interface OrderService {
    GetOrderResponse submitOrder(String username);

    List<GetOrderResponse> findAllOrdersByUser(String username);
}
