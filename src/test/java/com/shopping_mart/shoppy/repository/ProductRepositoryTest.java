package com.shopping_mart.shoppy.repository;

import com.shopping_mart.shoppy.entities.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(
        properties = {
                "spring.jpa.properties.javax.persistence.validation.mode=none"
        }
)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest {
    @Autowired
    private ProductRepository underTest;

    @Test
    void shouldFindAllProductsByName() {
        // Given
        Integer id = 2;
        String name = "product 2";
        BigDecimal price = BigDecimal.valueOf(30);
        String description = "details in product 2";

        Product product = Product.builder()
                .id(id)
                .name(name)
                .price(price)
                .description(description)
                .build();

        // when
        underTest.save(product);

        List<Product> products = underTest.findAllByName(name);

        Product retrievedProduct = products.get(0);

        // Then
        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals(id, retrievedProduct.getId());
    }

    @Test
    void shouldSaveProduct(){
        // Given
        Integer id = 1;
        String name = "product 1";
        BigDecimal price = BigDecimal.valueOf(20);
        String description = "details in product 1";

        Product product = Product.builder()
                .id(id)
                .name(name)
                .price(price)
                .description(description)
                .build();

        // when
        underTest.save(product);

        // Then
        Optional<Product> product1 = underTest.findById(id);

        assertTrue(product1.isPresent());
        assertEquals(name, product1.get().getName());
        assertEquals(price , product1.get().getPrice());
        assertEquals(description, product1.get().getDescription());

    }
}