package com.shopping_mart.shoppy.repository;

import com.shopping_mart.shoppy.entities.Order;
import com.shopping_mart.shoppy.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByUser(User user);
}
