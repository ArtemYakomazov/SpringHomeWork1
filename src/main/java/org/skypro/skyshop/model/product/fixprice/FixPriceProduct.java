package org.skypro.skyshop.model.product.fixprice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.product.Product;

import java.util.UUID;

public class FixPriceProduct extends Product {
    private static final int FIX_PRICE = 150;

    public FixPriceProduct(String name, UUID id) {
        super(name, id);
    }

    public double getPrice() {
        return FIX_PRICE;
    }

    @Override
    public String toString() {
        return getName() + ": Фиксированная цена " + FIX_PRICE;
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
