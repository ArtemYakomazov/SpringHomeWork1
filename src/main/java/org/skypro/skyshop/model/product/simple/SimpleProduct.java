package org.skypro.skyshop.model.product.simple;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.product.Product;

import java.util.UUID;

public class SimpleProduct extends Product {
    public int price;

    public SimpleProduct(String name, int price, UUID id) {
        super(name, id);
        if (price <= 0) {
            throw new IllegalArgumentException("Недействительная цена");
        }
        this.price = price;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return getName() + ": " + (int) getPrice();
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @JsonIgnore
    @Override
    public String searchTerm() {
        return super.searchTerm();
    }

    @JsonIgnore
    @Override
    public String getTypeContent() {
        return super.getTypeContent();
    }
}
