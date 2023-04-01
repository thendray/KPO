package org.kolpakoveethendray.models;

import java.util.Map;

/**
 * Класс-модель склада
 * @param products - продукты на складе
 */
public record Stock(Map<Product, Integer> products) {

    public void addProduct(Product product, int quantity) {
        products.put(product, products.get(product) + quantity);
    }

    /**
     * Взять продукты со склада
     * @param product - сам продукт
     * @param quantity - необходимое количество
     */
    public void takeProduct(Product product, int quantity) {
        products.put(product, products.get(product) - quantity);
    }
}
