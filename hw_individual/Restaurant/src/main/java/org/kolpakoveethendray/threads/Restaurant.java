package org.kolpakoveethendray.threads;

import org.kolpakoveethendray.config.RestaurantData;
import org.kolpakoveethendray.streams.ChefStream;
import org.kolpakoveethendray.models.Product;
import org.kolpakoveethendray.tools.Logger;
import org.kolpakoveethendray.models.Dish;
import org.kolpakoveethendray.models.Order;
import org.kolpakoveethendray.models.Time;
import org.kolpakoveethendray.streams.VisitorsStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;



public class Restaurant {

    static final boolean[] tables = new boolean[RestaurantData.getCountOfSits()];
    static final Semaphore semaphore = new Semaphore(RestaurantData.getCountOfSits(), true);


    private static int profit = 0;

    public void start() throws InterruptedException {
        BlockingQueue<Order> ordersFromVisitors = new LinkedBlockingQueue<>();
        BlockingQueue<Order> ordersForCooking = new LinkedBlockingQueue<>();
        BlockingQueue<Order> finishedOrders = new LinkedBlockingQueue<>();

        Logger.restaurantOpenLog();

        Thread managerThread = new Thread(new Manager(ordersFromVisitors, finishedOrders, ordersForCooking));
        managerThread.start();

        ChefStream kitchen = new ChefStream(ordersForCooking, finishedOrders);
        kitchen.start();

        VisitorsStream visitorsStream = new VisitorsStream(ordersFromVisitors, finishedOrders);
        visitorsStream.start();

        while (true) {
            if (System.currentTimeMillis() > Time.endTime) {
                visitorsStream.finish();

                kitchen.finish();

                managerThread.interrupt();

                managerThread.join();

                Logger.restaurantProfitLog(profit);

                Logger.restaurantCloseLog();
                return;
            }
        }
    }

    public static void addProfit(int money) {
        profit += money;
    }

    public static List<Dish> getActualMenu() {
        Map<Product, Integer> productsFromStock = RestaurantData.getStock().products();

        List<Dish> menu = RestaurantData.getMainMenu();
        List<Dish> actualMenu = new ArrayList<>();

        for (var dish : menu) {
            boolean ableToCook = true;

            var productsForDish = dish.ingredients();
            for (var productName : productsForDish.keySet()) {
                if (!productsFromStock.containsKey(productName) || productsForDish.get(productName) > productsFromStock.get(productName)) {
                    ableToCook = false;
                    break;
                }
            }

            if (ableToCook) {
                actualMenu.add(dish);

                for (var product : dish.ingredients().keySet()) {
                    productsFromStock.put(product, productsFromStock.get(product) - dish.ingredients().get(product));
                }
            }
        }

        return actualMenu;
    }
}
