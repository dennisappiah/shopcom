package com.shopping_mart.shoppy.repository;

import com.shopping_mart.shoppy.entities.Product;
import com.shopping_mart.shoppy.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
