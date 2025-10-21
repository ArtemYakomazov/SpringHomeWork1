package org.skypro.skyshop.model.product.discounted;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.product.Product;

import java.util.UUID;

public class DiscountedProduct extends Product {
    private final double basePrice;
    private final int discount;

    public DiscountedProduct(String name, double basePrice, int discount, UUID id) {
        super(name,id);
        if (basePrice <= 0) {
            throw new IllegalArgumentException("Недействительная цена");
        }
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Недействительный процент");
        }
        this.basePrice = basePrice;
        this.discount = discount;
    }

    public int getDiscount() {
        return discount;
    }

    @Override
    public double getPrice() {
        return basePrice * (1 - discount / 100d);
    }

    @Override
    public String toString() {
        return getName() + ": " + getPrice() + " (" + getDiscount() + "%)";
    }

    @Override
    public boolean isSpecial() {
        return true;
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
