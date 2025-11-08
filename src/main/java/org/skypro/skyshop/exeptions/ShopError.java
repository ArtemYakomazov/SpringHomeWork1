package org.skypro.skyshop.exeptions;

public class ShopError {
    String code;
    String message;

    public ShopError(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
