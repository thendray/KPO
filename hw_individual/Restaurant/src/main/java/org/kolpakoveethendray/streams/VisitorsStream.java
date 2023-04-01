package org.kolpakoveethendray.streams;

import org.kolpakoveethendray.config.RestaurantData;
import org.kolpakoveethendray.models.Order;
import org.kolpakoveethendray.threads.Visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Поток посетителей
 */
public class VisitorsStream {
    private final BlockingQueue<Order> ordersFromVisitors;
    private final BlockingQueue<Order> finishedOrders;
    private final AtomicInteger orderNumber;
    private final List<Thread> threads;

    public VisitorsStream(BlockingQueue<Order> ordersFromVisitors, BlockingQueue<Order> finishedOrders) {
        this.ordersFromVisitors = ordersFromVisitors;
        this.finishedOrders = finishedOrders;
        orderNumber = new AtomicInteger(1);
        threads = new ArrayList<>();
    }

    /**
     * Создаются потоки клиентов. Клиенты начинают идти в ресторан
     * @throws InterruptedException - кидает, если клиента не удается прервать
     */
    public void start() throws InterruptedException {
        for (int i = 0; i < RestaurantData.getCountOfVisitors(); i++) {
            Thread thread = new Thread(new Visitor(String.valueOf(i + 1), orderNumber, ordersFromVisitors, finishedOrders));
            threads.add(thread);
            thread.start();

            Thread.sleep((long) (60 + Math.random() * 300));
        }
    }

    /**
     * Клиенты завершают свою активную деятельность
     * @throws InterruptedException - кидает, если клиенты не могут прерваться.
     */
    public void finish() throws InterruptedException {
        for (var thread : threads) {
            thread.join();
        }
    }
}
