package org.kolpakoveethendray.streams;

import org.kolpakoveethendray.config.RestaurantData;
import org.kolpakoveethendray.threads.Chef;
import org.kolpakoveethendray.models.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * Класс-поток поваров
 */
public class ChefStream {
    private final BlockingQueue<Order> ordersForCooking;
    private final BlockingQueue<Order> finishedOrders;

    private final List<Thread> chefThreads;

    public ChefStream(BlockingQueue<Order> ordersForCooking, BlockingQueue<Order> finishedOrders) {
        this.ordersForCooking = ordersForCooking;
        this.finishedOrders = finishedOrders;
        chefThreads = new ArrayList<>();
    }

    /**
     * Создание потоков-поваров для работы кухни
     */
    public void start() {
        for (int i = 0; i < RestaurantData.getCountOfChefs(); i++) {
            Thread thread = new Thread(new Chef("Chef#" + (i + 1), ordersForCooking, finishedOrders));
            chefThreads.add(thread);
            thread.start();
        }
    }

    /**
     * Завершение потоков-поваров. Конец рабочего дня!
     * @throws InterruptedException - кидает, если не удается прервать поток
     */
    public void finish() throws InterruptedException {
        for (var chefThread : chefThreads) {
            chefThread.interrupt();
            chefThread.join();
        }
    }
}
