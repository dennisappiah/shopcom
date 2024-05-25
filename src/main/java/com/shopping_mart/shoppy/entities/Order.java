package com.shopping_mart.shoppy.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_tbl")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;


    @ManyToMany
    @JoinTable(
            name = "orders_products",
            joinColumns = {
                    @JoinColumn(name = "order_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "product_id")
            }
    )
    private List<Product> products;

    private BigDecimal total;

    public static Order createOrderFromCart(Cart cart) {
        Order order = new Order();

        order.setUser(cart.getUser());
        order.setTotal(cart.getTotal());
        order.setProducts(new ArrayList<>(cart.getProducts()));

        return order;
    }


}
