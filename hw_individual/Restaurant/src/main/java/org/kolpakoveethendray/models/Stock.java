package org.kolpakoveethendray.models;

import java.io.Serializable;
import java.util.Map;

public record Stock(Map<Product, Integer> products) implements Serializable {

    public void addProduct(Product product, int quantity) {
        products.put(product, products.get(product) + quantity);
    }

    public void takeProduct(Product product, int quantity) {
        products.put(product, products.get(product) - quantity);
    }
}
