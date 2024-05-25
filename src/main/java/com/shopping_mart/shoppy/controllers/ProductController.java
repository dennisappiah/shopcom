package com.shopping_mart.shoppy.controllers;

import com.shopping_mart.shoppy.dtos.CreateProductRequest;
import com.shopping_mart.shoppy.dtos.GetProductResponse;
import com.shopping_mart.shoppy.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products_all")
    public List<GetProductResponse> getAllProducts(){
        return productService.findAllProducts();
    }

    // products?page=5&size=10
    @GetMapping("/products")
    public Page<GetProductResponse> getPaginatedProducts(@RequestParam int page,
                                                         @RequestParam int size){
        return productService.findAllPaginatedProducts(page, size);
    }

    @GetMapping("/products/{id}")
    public GetProductResponse getProduct(@PathVariable Integer id){
        return productService.findById(id);
    }

    @GetMapping("/products/search/{name}")
    public List<GetProductResponse> getProductsByName(@PathVariable String name){
        return productService.findAllProductsByName(name);
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public GetProductResponse addProduct(@Valid @RequestBody
                                             CreateProductRequest request){
        return productService.createProduct(request);
    }
}
