package com.shopping_mart.shoppy.services;

import com.shopping_mart.shoppy.dtos.GetCartResponse;
import com.shopping_mart.shoppy.dtos.ModifyCartRequest;
import com.shopping_mart.shoppy.entities.Cart;

public interface CartService {
    GetCartResponse addProductToCart(ModifyCartRequest request);

    GetCartResponse removeProductToCart(ModifyCartRequest request);
}
