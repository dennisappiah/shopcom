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
@Table(name = "cart_tbl")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @OneToOne(mappedBy = "cart")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "carts_products",
            joinColumns = {
                    @JoinColumn(name = "cart_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "product_id")
            }
    )
    private List<Product> products;

    @Column
    private BigDecimal total;

    public <E> Cart(ArrayList<E> es) {
    }

    public void addProduct(Product product){
        if (product == null)
            this.products = new ArrayList<>();
        this.products.add(product);

        if(total == null)
            this.total = BigDecimal.ZERO;

        this.total = total.add(product.getPrice());
    }

    public void removeProduct(Product product){
        if (product == null) {
            return; // or throw IllegalArgumentException or handle the case appropriately
        }
        
        if(products == null)
            this.products = new ArrayList<>();
        this.products.remove(product);

        if(total == null)
            this.total = new BigDecimal(0);

        this.total = total.subtract(product.getPrice());
    }
}
