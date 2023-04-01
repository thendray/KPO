package org.kolpakoveethendray.config;

import org.kolpakoveethendray.models.Dish;
import org.kolpakoveethendray.models.Stock;

import java.io.Serializable;
import java.util.List;

/**
 * Класс-модель конфигурации ресторана - содержит необходимые данные для работы ресторана
 */
public class RestaurantData implements Serializable {
    private static int countOfChefs;
    private static int countOfVisitors;
    private static int countOfSits;
    private static int workHours;
    private static Stock stock;
    private static List<Dish> mainMenu;

    public static void setCountOfChefs(int countOfChefs) {
        RestaurantData.countOfChefs = countOfChefs;
    }

    public static void setCountOfVisitors(int countOfVisitors) {
        RestaurantData.countOfVisitors = countOfVisitors;
    }

    public static void setCountOfSits(int countOfSits) {
        RestaurantData.countOfSits = countOfSits;
    }

    public static void setWorkHours(int workHours) {
        RestaurantData.workHours = workHours;
    }

    public static void setStock(Stock stock) {
        RestaurantData.stock = stock;
    }

    public static void setMainMenu(List<Dish> mainMenu) {
        RestaurantData.mainMenu = mainMenu;
    }

    public static int getCountOfChefs() {
        return countOfChefs;
    }

    public static int getCountOfVisitors() {
        return countOfVisitors;
    }

    public static int getCountOfSits() {
        return countOfSits;
    }

    public static int getWorkHours() {
        return workHours;
    }

    public static Stock getStock() {
        return stock;
    }

    public static List<Dish> getMainMenu() {
        return mainMenu;
    }
}
