package org.skypro.skyshop.controller;

import org.skypro.skyshop.exeptions.NoSuchProductException;
import org.skypro.skyshop.exeptions.ShopError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ShopControllerAdvice {
    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<ShopError> noSuchProductHandler(NoSuchProductException e) {
        ShopError shopError = new ShopError("NO_PRODUCT", "Товар не найден");

        return new ResponseEntity<>(shopError, HttpStatus.NOT_FOUND);
    }
}
