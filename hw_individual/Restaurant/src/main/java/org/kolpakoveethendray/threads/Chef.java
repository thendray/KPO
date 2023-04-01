package org.kolpakoveethendray.threads;

import org.kolpakoveethendray.tools.Logger;
import org.kolpakoveethendray.models.Order;

import java.util.concurrent.BlockingQueue;

public class Chef implements Runnable {
    private final String name;
    private final BlockingQueue<Order> orders;
    private final BlockingQueue<Order> finishedOrders;

    public Chef(String name, BlockingQueue<Order> orders, BlockingQueue<Order> finishedOrders) {
        this.name = name;
        this.orders = orders;
        this.finishedOrders = finishedOrders;
    }

    public String getName() {
        return name;
    }

    /**
     * Поток повара стартует. Повар выходит на работу и ждет пока у него появится заказ.
     */
    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                // Принимает заказ
                Order order = orders.take();

                Logger.chefCookOrderLog(this, order);

                // Готовит заказ
                Thread.sleep(order.getTimeOfCooking());

                Logger.chefFinishCookLog(this, order);
                // Отправляет готовый заказ на раздачу
                finishedOrders.put(order);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Logger.chefLeaveLog(this);
    }
}
