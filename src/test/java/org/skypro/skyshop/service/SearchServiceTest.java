package org.skypro.skyshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.simple.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    @Test
    public void testSearch_NoProducts() {
        when(storageService.getAllProducts()).thenReturn(Collections.emptyList());
        List<SearchResult> results = searchService.search("Хлеб");
        assertTrue(results.isEmpty());
    }

    @Test
    public void testSearch_NoMatchingProducts() {
        Product product1 = new SimpleProduct("Молоко", 60, UUID.randomUUID());
        when(storageService.getAllProducts()).thenReturn(Collections.singletonList(product1));
        List<SearchResult> results = searchService.search("Хлеб");
        assertTrue(results.isEmpty());
    }

    @Test
    public void testSearch_MatchingProduct() {
        Product product1 = new SimpleProduct("Хлеб", 30, UUID.randomUUID());
        when(storageService.getAllProducts()).thenReturn(Collections.singletonList(product1));
        List<SearchResult> results = searchService.search("Хлеб");
        assertEquals(1, results.size());
        assertEquals("Хлеб", results.get(0).getName());
    }
}
