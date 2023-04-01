package org.kolpakoveethendray.threads;

import org.kolpakoveethendray.config.RestaurantData;
import org.kolpakoveethendray.tools.Logger;
import org.kolpakoveethendray.models.Order;
import org.kolpakoveethendray.models.Time;

import java.util.concurrent.BlockingQueue;

public class Manager implements Runnable {
    private final BlockingQueue<Order> orders;
    private final BlockingQueue<Order> finishedOrders;
    private final BlockingQueue<Order> toKitchen;

    public Manager(BlockingQueue<Order> orders, BlockingQueue<Order> finishedOrders, BlockingQueue<Order> toKitchen) {
        this.orders = orders;
        this.finishedOrders = finishedOrders;
        this.toKitchen = toKitchen;
    }

    private void reserveProductsForOrder(Order order) {
        for (var dish : order.getDishes()) {
            for (var product : dish.ingredients().keySet()) {
                RestaurantData.getStock().takeProduct(product, dish.ingredients().get(product));
            }
        }
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Order order = orders.take();
                reserveProductsForOrder(order);

                long total = order.getTimeOfCooking();
                for (var ord : toKitchen) {
                    total += ord.getTimeOfCooking();
                }

                if (System.currentTimeMillis() + total / RestaurantData.getCountOfChefs() > Time.endTime) {
                    order.setIsCancelled(true);
                    Logger.managerRejectOrderLog(order);
                    finishedOrders.put(order);
                    continue;
                }

                Logger.managerTakeOrderLog(order, total / 60 / RestaurantData.getCountOfChefs());
                toKitchen.put(order);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Logger.managerLeaveLog();
    }
}
