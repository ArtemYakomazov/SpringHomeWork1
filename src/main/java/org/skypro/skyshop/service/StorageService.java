package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.busket.ProductBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.discounted.DiscountedProduct;
import org.skypro.skyshop.model.product.fixprice.FixPriceProduct;
import org.skypro.skyshop.model.product.simple.SimpleProduct;
import org.skypro.skyshop.model.search.SearchEngine;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private final Map<UUID, Product> uuidProductMap;
    private final Map<UUID, Article> uuidArticleMap;
    private final Map<UUID, Product> availableProduct;

    public StorageService(Map<UUID, Product> uuidProductMap, Map<UUID, Article> uuidArticleMap, Map<UUID, Product> availableProduct) {
        this.uuidProductMap = new HashMap<>();
        this.uuidArticleMap = new HashMap<>();
        this.availableProduct = new HashMap<>();
        getTestMap();

    }

    public Collection<Product> getAllProducts() {
        return uuidProductMap.values();
    }

    public Collection<Article> getAllArticles() {
        return uuidArticleMap.values();
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(availableProduct.get(id));
    }

    private void getTestMap() {
        ProductBasket productBasket = new ProductBasket();
        Product product1 = new SimpleProduct("Хлеб", 30, UUID.randomUUID());
        Product product2 = new DiscountedProduct("Молоко", 60, 12, UUID.randomUUID());
        Product product3 = new DiscountedProduct("Колбаса", 12, 15, UUID.randomUUID());
        Product product4 = new FixPriceProduct("Сыр", UUID.randomUUID());
        Product product5 = new FixPriceProduct("Шампиньоны", UUID.randomUUID());
        productBasket.addProduct(product1.getId());
        productBasket.addProduct(product2.getId());
        productBasket.addProduct(product3.getId());
        productBasket.addProduct(product4.getId());
        productBasket.addProduct(product5.getId());

        SearchEngine engine = new SearchEngine();
        Article article1 = new Article("Сыр", "Благородные сыры", UUID.randomUUID());
        Article article2 = new Article("Хлеб", "Производство хлеба", UUID.randomUUID());
        Article article3 = new Article("Колбаски", "Колбасные нарезки", UUID.randomUUID());
        Article article4 = new Article("Шампиньоны", "Выращивание шампиньонов", UUID.randomUUID());
        engine.add(article1,article1.getId());
        engine.add(article2,article2.getId());
        engine.add(article3,article3.getId());
        engine.add(article4,article4.getId());
    }
}
