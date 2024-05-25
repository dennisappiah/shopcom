package com.shopping_mart.shoppy.controllers;

import com.shopping_mart.shoppy.dtos.GetOrderResponse;
import com.shopping_mart.shoppy.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public GetOrderResponse submitOrder(@RequestBody String username){
        return orderService.submitOrder(username);
    }

    @GetMapping("/orders/{username}")
    public List<GetOrderResponse> getHistoricalOrdersByUser(@PathVariable String username){
        return orderService.findAllOrdersByUser(username);
    }
}
