package org.kolpakoveethendray.models;

import java.util.Map;

/**
 * Класс-модель блюда ресторана
 * @param name - название блюда
 * @param ingredients - продукты, необходимые для приготовления блюда ("Название": количество)
 * @param price - цена блюда
 * @param timeOfCooking - время приготовления блюда
 */
public record Dish(
        String name,
        Map<Product, Integer> ingredients,
        int price,
        long timeOfCooking
) {
}
