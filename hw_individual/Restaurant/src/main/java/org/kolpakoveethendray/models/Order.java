package org.kolpakoveethendray.models;

import java.util.List;

/**
 * Класс-модель заказа
 */
public class Order {

    private final int orderNumber;
    private final List<Dish> dishes;
    private final int price;
    private boolean isCancelled;

    public boolean isCancelled() {
        return isCancelled;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setIsCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public int getPrice() {
        return price;
    }

    public Order(int orderNumber, List<Dish> dishes, int price) {
        this.orderNumber = orderNumber;
        this.dishes = dishes;
        this.price = price;
        isCancelled = false;
    }

    public long getTimeOfCooking() {
        long total = 0;
        for (var dish : dishes){
            total += dish.timeOfCooking();
        }

        return total;
    }

    @Override
    public String toString() {
        if (dishes.size() == 0) {
            return "empty order";
        }
        StringBuilder sb = new StringBuilder();

        for (var dish : dishes) {
            sb.append(dish.name()).append(", ");
        }

        return sb.substring(0, sb.length() - 2);
    }
}
