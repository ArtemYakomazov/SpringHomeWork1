package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {
    public String name;
    private final UUID id;

    public Product(String name, UUID id) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Строка названия пустая или состоит из пробелов");
        }
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public abstract double getPrice();

    public abstract boolean isSpecial();

    @JsonIgnore
    @Override
    public String searchTerm() {
        return getName();
    }
    @JsonIgnore
    @Override
    public String getTypeContent() {
        return "PRODUCT";
    }

    @Override
    public String getStringRepresentation() {
        return Searchable.super.getStringRepresentation();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(getName(), product.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }

    @Override
    public UUID getId() {
        return id;
    }
}
