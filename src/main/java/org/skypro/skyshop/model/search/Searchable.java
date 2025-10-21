package org.skypro.skyshop.model.search;


import java.util.UUID;

public interface Searchable {
    String searchTerm();
    String getTypeContent();
    String getName();


    default String getStringRepresentation() {
        return getName() + getTypeContent();
    }

    UUID getId();
}
