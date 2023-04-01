package org.kolpakoveethendray.threads;

import org.kolpakoveethendray.config.RestaurantData;
import org.kolpakoveethendray.tools.Logger;
import org.kolpakoveethendray.models.Dish;
import org.kolpakoveethendray.models.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Поток - посетитель
 */
public class Visitor implements Runnable {
    private final String name;
    private final AtomicInteger orderNumber;
    private final BlockingQueue<Order> orders;
    private final BlockingQueue<Order> finishedOrders;

    public Visitor(String name, AtomicInteger orderNumber, BlockingQueue<Order> orders, BlockingQueue<Order> finishedOrders) {
        this.orderNumber = orderNumber;
        this.orders = orders;
        this.finishedOrders = finishedOrders;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * Сделать заказ
     * @param actualMenu - меню, из которого можно выбрать блюда
     * @return - заказ со списком выбранных блюд
     */
    public Order makeOrder(List<Dish> actualMenu) {
        if (actualMenu.size() == 0) {
            Order order = new Order(orderNumber.get(), new ArrayList<>(), 0);
            order.setIsCancelled(true);
            return order;
        }
        List<Dish> dishes = new ArrayList<>();
        int quantityOfDishes = actualMenu.size();

        int quantityDishesInOrder = 1 + (int)(Math.random() * 3);

        for (int i = 0; i < quantityDishesInOrder; i++) {
            int numberOfDish = ((int) (Math.random() * quantityOfDishes)) % quantityOfDishes;

            dishes.add(actualMenu.get(numberOfDish));
        }

        int price = 0;

        for (var dish : dishes){
            price += dish.price();
        }

        return new Order(orderNumber.getAndIncrement(), dishes, price);
    }

    /**
     * Поток Клиент начинает работу и стучится в ресторан. Если есть свободные столики, то заходит, иначе ждет.
     */
    @Override
    public void run() {
        try {
            Restaurant.semaphore.acquire();

            int tableNumber = -1;

            //Ищем свободное место и паркуемся
            synchronized (Restaurant.tables) {
                for (int i = 0; i < RestaurantData.getCountOfSits(); i++)
                    if (!Restaurant.tables[i]) {      //Если место свободно
                        Restaurant.tables[i] = true;  //занимаем его
                        tableNumber = i;         //Наличие свободного места, гарантирует семафор
                        break;
                    }
            }

            Logger.visitorEnterLog(this);

            // ищем столик 2 минуты
            Thread.sleep(120);

            Logger.visitorSitDownLog(this, tableNumber + 1);

            Logger.visitorLookMenuLog(this);

            // Смотрим в меню и выбираем блюда
            Thread.sleep(300);

            // Делаем заказ
            Order order = makeOrder(Restaurant.getActualMenu());

            // Если заказ отменен (по причине высокой занятости кухни), то уходим
            if (order.isCancelled()){
                Logger.visitorLeaveLog(this);
                synchronized (Restaurant.tables) {
                    Restaurant.tables[tableNumber] = false;
                }
                Restaurant.semaphore.release();
                return;
            }

            Logger.visitorMakeOrderLog(this, order);

            orders.put(order);

            // Ждем пока приготовят (на раздаче появится заказ с нашим номером)
            while (!finishedOrders.contains(order)) {
                continue;
            }

            finishedOrders.take();

            if (order.isCancelled()) {
                Logger.visitorLeaveLog(this);
                synchronized (Restaurant.tables) {
                    Restaurant.tables[tableNumber] = false;
                }

                Restaurant.semaphore.release();
                return;
            }

            // Кушаем
            Thread.sleep(order.getTimeOfCooking() / 2);

            // Рассчитываемся за заказ и уходим
            Logger.visitorPayLog(this, order);
            Restaurant.addProfit(order.getPrice());

            Logger.visitorLeaveLog(this);

            synchronized (Restaurant.tables) {
                Restaurant.tables[tableNumber] = false;
            }
            Restaurant.semaphore.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
