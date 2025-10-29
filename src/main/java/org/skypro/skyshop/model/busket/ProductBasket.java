package org.skypro.skyshop.model.busket;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ProductBasket {
    private final Map<UUID, Integer> products;


    public ProductBasket() {
        this.products = new HashMap<>();
    }


    public void addProduct(UUID id) {
        products.put(id, products.get(id));
    }

    @Bean
    public Map<UUID, Integer> getProducts() {
        return Collections.unmodifiableMap(products);
    }
}