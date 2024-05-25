package com.shopping_mart.shoppy.services;

import com.shopping_mart.shoppy.dtos.CreateProductRequest;
import com.shopping_mart.shoppy.dtos.GetProductResponse;
import org.springframework.data.domain.Page;
import java.util.List;


public interface ProductService {
    List<GetProductResponse> findAllProducts();

    Page<GetProductResponse> findAllPaginatedProducts(int page, int size);

    List<GetProductResponse> findAllProductsByName(String name);

    GetProductResponse findById(Integer id);

    GetProductResponse createProduct(CreateProductRequest request);
}
