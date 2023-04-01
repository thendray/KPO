package org.kolpakoveethendray.models;

import java.io.Serializable;
import java.util.Map;

public record Dish(
        String name,
        Map<Product, Integer> ingredients,
        int price,
        long timeOfCooking
)  {
}
