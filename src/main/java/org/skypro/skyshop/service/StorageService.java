package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.discounted.DiscountedProduct;
import org.skypro.skyshop.model.product.fixprice.FixPriceProduct;
import org.skypro.skyshop.model.product.simple.SimpleProduct;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private final Map<UUID, Product> uuidProductMap;
    private final Map<UUID, Article> uuidArticleMap;

    public StorageService() {
        this.uuidProductMap = new HashMap<>();
        this.uuidArticleMap = new HashMap<>();
        getTestMap();

    }

    public Collection<Product> getAllProducts() {
        return uuidProductMap.values();
    }
    public Collection<Article> getAllArticles() {
        return uuidArticleMap.values();
    }
    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(uuidProductMap.get(id));
    }

    private void getTestMap() {
        Product product1 = new SimpleProduct("Хлеб", 30, UUID.randomUUID());
        Product product2 = new DiscountedProduct("Молоко", 60, 12, UUID.randomUUID());
        Product product3 = new DiscountedProduct("Колбаса", 12, 15, UUID.randomUUID());
        Product product4 = new FixPriceProduct("Сыр", UUID.randomUUID());
        Product product5 = new FixPriceProduct("Шампиньоны", UUID.randomUUID());
        uuidProductMap.put(product1.getId(), product1);
        uuidProductMap.put(product2.getId(), product2);
        uuidProductMap.put(product3.getId(), product3);
        uuidProductMap.put(product4.getId(), product4);
        uuidProductMap.put(product5.getId(), product5);


        Article article1 = new Article("Сыр", "Благородные сыры", UUID.randomUUID());
        Article article2 = new Article("Хлеб", "Производство хлеба", UUID.randomUUID());
        Article article3 = new Article("Колбаски", "Колбасные нарезки", UUID.randomUUID());
        Article article4 = new Article("Шампиньоны", "Выращивание шампиньонов", UUID.randomUUID());

    }
}
