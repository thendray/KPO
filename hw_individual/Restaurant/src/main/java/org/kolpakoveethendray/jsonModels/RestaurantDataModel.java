package org.kolpakoveethendray.jsonModels;

import org.kolpakoveethendray.models.Dish;
import org.kolpakoveethendray.models.Stock;

import java.io.Serializable;
import java.util.List;

public record RestaurantDataModel(
        int countOfChefs,
        int countOfVisitors,
        int countOfSits,
        int workHours,
        Stock stock,
        List<Dish> mainMenu
) implements Serializable {
}
