package com.shopping_mart.shoppy.repository;

import com.shopping_mart.shoppy.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByName(String name);
}
