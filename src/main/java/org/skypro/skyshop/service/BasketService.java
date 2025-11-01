package org.skypro.skyshop.service;

import org.skypro.skyshop.model.busket.BasketItem;
import org.skypro.skyshop.model.busket.ProductBasket;
import org.skypro.skyshop.model.busket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;


    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addProductToBasket(UUID id) {
        Optional<Product> productOptional = storageService.getProductById(id);
        if (!productOptional.isPresent()) {
            throw new IllegalArgumentException("Товар с таким ID не найден.");
        }
        productBasket.addProduct(id);
    }

    public UserBasket getUserBasket() {
        List<BasketItem> basketItems = productBasket
                .getProducts()
                .entrySet()
                .stream()
                .map(m -> new BasketItem(storageService
                                .getProductById(m.getKey())
                                .orElseThrow(() -> new IllegalArgumentException("Продукт не найден по id: " + m.getKey())), m.getValue()))
                .collect(Collectors.toCollection(ArrayList::new));

        double total = basketItems.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();

        return new UserBasket(basketItems, total);
    }
}
