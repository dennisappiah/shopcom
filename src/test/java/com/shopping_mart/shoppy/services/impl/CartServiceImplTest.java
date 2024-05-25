package com.shopping_mart.shoppy.services.impl;

import com.shopping_mart.shoppy.dtos.GetCartResponse;
import com.shopping_mart.shoppy.dtos.ModifyCartRequest;
import com.shopping_mart.shoppy.entities.Cart;
import com.shopping_mart.shoppy.entities.Product;
import com.shopping_mart.shoppy.entities.User;
import com.shopping_mart.shoppy.repository.CartRepository;
import com.shopping_mart.shoppy.repository.ProductRepository;
import com.shopping_mart.shoppy.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.mockito.ArgumentMatchers.any;

import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CartServiceImplTest {

    @InjectMocks
    private CartServiceImpl underTest;

    @Mock
    private UserRepository userRepository;
    @Mock
    private  ProductRepository productRepository;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private  ModelMapper cartMapper;

    @Captor
    private ArgumentCaptor<Cart> cartCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        underTest = new CartServiceImpl(userRepository,
                productRepository, cartRepository, cartMapper);
    }

    @Test
    public void should_add_product_to_cart(){
        // Given
        Integer productId = 1;
        int quantity = 5;
        String username = "kofi";

        ModifyCartRequest request = ModifyCartRequest.builder()
                .productId(productId)
                .quantity(quantity)
                .username(username)
                .build();

        //...mocked user
        Integer userId = 1;

        User user = User.builder()
                .id(userId)
                .password("abc")
                .cart(new Cart(new ArrayList<>()))
                .username(username)
                .build();

        // ... mocked product
        String name = "product 1";
        BigDecimal price = BigDecimal.valueOf(20);
        String description = "details in product 1";

        Product product = Product.builder()
                .id(productId)
                .name(name)
                .price(price)
                .description(description)
                .build();

        // When
        GetCartResponse fakeResponse = new GetCartResponse();
        when(cartMapper.map(any(), any())).thenReturn(fakeResponse);


        when(userRepository.findByUsername(username)).thenReturn(user);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        GetCartResponse cart = underTest.addProductToCart(request);

        // Then
        verify(cartRepository).save(any(Cart.class));

        Cart savedcart = cartCaptor.getValue();

        assertNotNull(savedcart);
    }

}