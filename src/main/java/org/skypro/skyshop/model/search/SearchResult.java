package org.skypro.skyshop.model.search;

import java.util.UUID;

 public class SearchResult{
    final UUID id;
    final String name;

     public SearchResult(UUID id, String name) {
         this.id = id;
         this.name = name;

     }

     public UUID getId() {
         return id;
     }

     public String getName() {
         return name;
     }

     public static SearchResult fromSearchable(Searchable searchable) {
         return new SearchResult(searchable.getId(), searchable.getName());
     }
 }
