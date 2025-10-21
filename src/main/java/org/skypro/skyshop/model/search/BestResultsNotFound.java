package org.skypro.skyshop.model.search;

public class BestResultsNotFound extends Exception {

    public BestResultsNotFound(String query) {
        super("Подходящий результат не найден для " + query);
    }
}
