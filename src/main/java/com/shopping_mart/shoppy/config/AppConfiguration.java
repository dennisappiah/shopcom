package com.shopping_mart.shoppy.config;

import com.github.javafaker.Faker;
import com.shopping_mart.shoppy.entities.Product;
import com.shopping_mart.shoppy.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class AppConfiguration {
    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }


//    @Bean
    public CommandLineRunner commandLineRunner(ProductRepository productRepository){
        return args -> {
            Faker faker = new Faker();

            for (int i = 0; i < 100; i++) {
                var product = Product.builder()
                        .name(faker.name().name())
                        .price(BigDecimal.valueOf(faker.number().randomDigit()))
                        .description(faker.funnyName().name())
                        .build();

                productRepository.save(product);
            }
        };
    }
}
