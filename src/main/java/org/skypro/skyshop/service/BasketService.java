package org.skypro.skyshop.service;

import org.skypro.skyshop.model.busket.ProductBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
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
        Map<UUID, Integer> products = productBasket.getProducts();
        List<BasketItem> basketItems = products.entrySet().stream()
                .map(entry -> {
                    Product product = storageService.getProductById(entry.getKey()).orElse(null);
                    return new BasketItem(product, entry.getValue());
                })
                .collect(Collectors.toList());

        double total = basketItems.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();

        return new UserBasket(basketItems, total);
    }
}
