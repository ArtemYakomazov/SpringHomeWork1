package org.skypro.skyshop.exeptions;

public class NoSuchProductException extends RuntimeException {

    public NoSuchProductException() {
        super("Такого продукта нет");
    }
}
