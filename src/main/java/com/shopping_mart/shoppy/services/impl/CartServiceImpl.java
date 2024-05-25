package com.shopping_mart.shoppy.services.impl;

import com.shopping_mart.shoppy.dtos.GetCartResponse;
import com.shopping_mart.shoppy.dtos.ModifyCartRequest;
import com.shopping_mart.shoppy.entities.Cart;
import com.shopping_mart.shoppy.entities.Product;
import com.shopping_mart.shoppy.entities.User;
import com.shopping_mart.shoppy.exception.ProductNotFoundException;
import com.shopping_mart.shoppy.exception.UserNotFoundException;
import com.shopping_mart.shoppy.repository.CartRepository;
import com.shopping_mart.shoppy.repository.ProductRepository;
import com.shopping_mart.shoppy.repository.UserRepository;
import com.shopping_mart.shoppy.services.CartService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    private final CartRepository cartRepository;

    private final ModelMapper cartMapper;

    @Override
    public GetCartResponse addProductToCart(ModifyCartRequest request) {
        // get user associated with cart
        User user = userRepository.findByUsername(request.getUsername());

        if (user == null) {
            throw new UserNotFoundException("User not found with username: " + request.getUsername());
        }

        // get product to add to cart
        Product product = productRepository.findById(request.getProductId()).orElseThrow(
                ()-> new ProductNotFoundException("product with id does not exist")
        );

        Cart cart = user.getCart();

        // Create a new cart if it doesn't exist
        if (cart == null) {
            cart = new Cart();
            user.setCart(cart);
        }

        int quantity = request.getQuantity();

        for (int i = 0; i < quantity; i++) {
            cart.addProduct(product);
        }

        cartRepository.save(cart);

        return cartMapper.map(cart, GetCartResponse.class);
    }


    @Override
    public GetCartResponse removeProductToCart(ModifyCartRequest request) {
        // get user associated with cart
        User user = userRepository.findByUsername(request.getUsername());

        // get product
        Product product = productRepository.findById(request.getProductId()).orElseThrow();

        Cart cart = user.getCart();

        int quantity = request.getQuantity();

        for (int i = 0; i < quantity; i++) {
            cart.removeProduct(product);
        }

        cartRepository.save(cart);

        return cartMapper.map(cart,GetCartResponse.class);
    }
}
