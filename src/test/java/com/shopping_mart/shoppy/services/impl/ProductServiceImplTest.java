package com.shopping_mart.shoppy.services.impl;

import com.shopping_mart.shoppy.dtos.CreateProductRequest;
import com.shopping_mart.shoppy.dtos.GetProductResponse;
import com.shopping_mart.shoppy.entities.Product;
import com.shopping_mart.shoppy.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProductServiceImplTest {
    @InjectMocks
    private ProductServiceImpl underTest;

    @Mock
    private  ProductRepository productRepository;
    @Mock
    private ModelMapper productMapper;

    @Captor
    private ArgumentCaptor<Product> productCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new ProductServiceImpl(productRepository, productMapper);
    }

    @Test
    public void should_successfully_save_product() {
        // Given
        String name = "product 1";
        BigDecimal price = BigDecimal.valueOf(20);
        String description = "details in product 1";

        CreateProductRequest request = CreateProductRequest.builder()
                .name(name)
                .price(price)
                .description(description)
                .build();


       // When
        GetProductResponse fakeResponse = new GetProductResponse();
        when(productMapper.map(any(), any())).thenReturn(fakeResponse);

        GetProductResponse product = underTest.createProduct(request);

        // Then
        verify(productRepository).save(productCaptor.capture());

        Product savedProduct = productCaptor.getValue();

        assertNotNull(savedProduct);
        assertEquals(request.getName(), savedProduct.getName());
        assertEquals(request.getPrice(), savedProduct.getPrice());
        assertEquals(request.getDescription(), savedProduct.getDescription());

    }

    @Test
    void should_successfully_fetch_all_products(){
        // Given
        String name = "product 1";
        BigDecimal price = BigDecimal.valueOf(20);
        String description = "details in product 1";

        //...mocked product
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);

        //...mocked response
        List<Product> productList = Collections.singletonList(product);
        when(productRepository.findAll()).thenReturn(productList);

        // When
        GetProductResponse fakeResponse = new GetProductResponse();
        when(productMapper.map(any(), any())).thenReturn(fakeResponse);

        List<GetProductResponse> products = underTest.findAllProducts();

        // Then
        verify(productRepository).findAll();

        assertNotNull(products);
        assertEquals(1, products.size());
       // assertEquals(name, products.get(0).getName());
        //assertEquals(price, products.get(0).getPrice());
        //assertEquals(description, products.get(0).getDescription());
    }

}