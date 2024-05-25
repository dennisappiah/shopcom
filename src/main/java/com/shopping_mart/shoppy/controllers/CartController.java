package com.shopping_mart.shoppy.controllers;

import com.shopping_mart.shoppy.dtos.GetCartResponse;
import com.shopping_mart.shoppy.dtos.ModifyCartRequest;
import com.shopping_mart.shoppy.services.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/carts/add")
    @ResponseStatus(HttpStatus.CREATED)
    public GetCartResponse addToCart(@Valid @RequestBody ModifyCartRequest request){
        return cartService.addProductToCart(request);
    }

    @PostMapping("/carts/remove")
    @ResponseStatus(HttpStatus.CREATED)
    public GetCartResponse removeFromCart(@Valid @RequestBody ModifyCartRequest request){
        return cartService.removeProductToCart(request);
    }
}
