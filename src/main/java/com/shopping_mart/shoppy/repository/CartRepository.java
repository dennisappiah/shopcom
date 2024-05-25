package com.shopping_mart.shoppy.repository;

import com.shopping_mart.shoppy.entities.Cart;
import com.shopping_mart.shoppy.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {

}
