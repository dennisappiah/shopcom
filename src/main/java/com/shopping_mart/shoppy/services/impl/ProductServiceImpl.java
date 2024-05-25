package com.shopping_mart.shoppy.services.impl;

import com.shopping_mart.shoppy.dtos.CreateProductRequest;
import com.shopping_mart.shoppy.dtos.GetProductResponse;
import com.shopping_mart.shoppy.entities.Product;
import com.shopping_mart.shoppy.exception.ProductNotFoundException;
import com.shopping_mart.shoppy.repository.ProductRepository;
import com.shopping_mart.shoppy.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ModelMapper productMapper;
    @Override
    public List<GetProductResponse> findAllProducts() {
        return productRepository.findAll().
                stream().map(product -> productMapper.map(product, GetProductResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public Page<GetProductResponse> findAllPaginatedProducts(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        return productRepository.findAll(pageRequest)
                .map(product -> productMapper.map(product, GetProductResponse.class));
    }

    @Override
    public List<GetProductResponse> findAllProductsByName(String name) {
        return productRepository.findAllByName(name).
                stream().map(product -> productMapper.map(product, GetProductResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public GetProductResponse findById(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("product with id does not exist "));

        return productMapper.map(product, GetProductResponse.class);

    }

    @Override
    public GetProductResponse createProduct(CreateProductRequest request) {
        Product product = new Product();

        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());

        productRepository.save(product);

        return productMapper.map(product, GetProductResponse.class);
    }
}
