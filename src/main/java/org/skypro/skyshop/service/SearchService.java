package org.skypro.skyshop.service;

import org.skypro.skyshop.model.search.SearchResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public List<SearchResult> search(String term) {
        return storageService.getAllProducts().stream()
                .filter(s -> s.searchTerm().contains(term))
                .map(SearchResult::fromSearchable)
                .collect(Collectors.toList());
    }
}

