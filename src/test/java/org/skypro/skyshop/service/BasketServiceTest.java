package org.skypro.skyshop.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.exeptions.NoSuchProductException;
import org.skypro.skyshop.model.busket.ProductBasket;
import org.skypro.skyshop.model.busket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.simple.SimpleProduct;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {

    @Mock
    private StorageService storageService;
    @Mock
    private ProductBasket productBasket;
    @InjectMocks
    private BasketService basketService;

    @Test
    public void testAddNonExistentProduct() {
        UUID nonExistentProductId = UUID.randomUUID();
        when(storageService.getProductById(nonExistentProductId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(NoSuchProductException.class, () -> {
            basketService.addProductToBasket(nonExistentProductId);
        });

        assertNotNull(exception, "Исключение не должно быть null");
        verify(productBasket, never()).addProduct(any());
    }

    @Test
    public void testAddExistingProduct() {
        UUID existentProductId = UUID.randomUUID();
        Product product = new SimpleProduct("Test Product", 100, existentProductId);
        when(storageService.getProductById(existentProductId)).thenReturn(Optional.of(product));

        basketService.addProductToBasket(existentProductId);

        verify(productBasket, times(1)).addProduct(existentProductId);
    }

    @Test
    public void testGetUserBasket_Empty() {
        when(productBasket.getProducts()).thenReturn(Collections.emptyMap());

        UserBasket result = basketService.getUserBasket();

        assertTrue(result.getItems().isEmpty(), "Корзина должна быть пустой.");
        assertEquals(0.0, result.getTotal(), "Общая сумма должна быть 0.");
    }

    @Test
    public void testGetUserBasket_NonEmpty() {
        UUID productId = UUID.randomUUID();
        Product product = new SimpleProduct("Test Product", 100, productId);
        when(productBasket.getProducts()).thenReturn(Map.of(productId, 2));
        when(storageService.getProductById(productId)).thenReturn(Optional.of(product));

        UserBasket result = basketService.getUserBasket();

        assertEquals(1, result.getItems().size(), "Корзина должна содержать один элемент.");
        assertEquals(200.0, result.getTotal(), "Общая сумма должна быть 200.");
    }
}
