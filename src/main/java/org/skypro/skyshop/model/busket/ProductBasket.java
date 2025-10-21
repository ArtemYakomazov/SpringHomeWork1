package org.skypro.skyshop.model.busket;

import org.skypro.skyshop.model.product.Product;

import java.util.*;

public class ProductBasket {
    private final Map<String, List<Product>> products;


    public ProductBasket() {
        this.products = new HashMap<>();
    }

    public void addProduct(Product product,UUID id) {
        if (!products.containsKey(product.getName())) {
            products.put(product.getName(), new LinkedList<>());
        }
        products.get(product.getName()).add(product);
    }

    public double getTotalPrice() {
        return products.values().stream().flatMap(Collection::stream)
                .mapToDouble(Product::getPrice)
                .sum();
    }

    public void printProductBasket() {
        if (products.isEmpty()) {
            System.out.println("В корзине пусто");
            return;
        }
        int specialCount = getSpecialCount();
        products.values().stream().flatMap(Collection::stream).forEach(System.out::println);
        System.out.println("Общая стоимость корзины: " + getTotalPrice());
        System.out.println("Специальных товаров: " + specialCount);
    }

    private int getSpecialCount() {
        return (int) products.values().stream().flatMap(Collection::stream)
                .filter(Product::isSpecial)
                .count();

    }

    public void deleteProductByName(String name) {
        List<Product> deleteProducts = new ArrayList<>();
        for (List<Product> productList : products.values()) {
            Iterator<Product> iterator = productList.iterator();
            while (iterator.hasNext()) {
                Product product = iterator.next();
                if (product.getName().equals(name)) {
                    deleteProducts.add(product);
                    iterator.remove();
                }
            }
        }
        if (deleteProducts.isEmpty()) {
            System.out.println("Список пуст");
        } else {
            System.out.println("Удаленные продукты: " + deleteProducts);
        }
    }
}