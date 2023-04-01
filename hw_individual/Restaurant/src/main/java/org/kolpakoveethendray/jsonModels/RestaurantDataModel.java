package org.kolpakoveethendray.jsonModels;

import org.kolpakoveethendray.models.Dish;
import org.kolpakoveethendray.models.Stock;

import java.util.List;

/**
 * Класс-модель для чтения данных из json файла
 * @param countOfChefs - количество поваров на кухне
 * @param countOfVisitors - количество посетителей
 * @param countOfSits - количество мест в ресторане
 * @param workHours - количество часов работы ресторана
 * @param stock - склад с продуктами
 * @param mainMenu - основное меню ресторана
 */
public record RestaurantDataModel(
        int countOfChefs,
        int countOfVisitors,
        int countOfSits,
        int workHours,
        Stock stock,
        List<Dish> mainMenu
) {
}
