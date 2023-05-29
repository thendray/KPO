package ru.thendray.restaurantapi.events;

import org.springframework.stereotype.Component;
import ru.thendray.restaurantapi.entities.OrderEntity;
import ru.thendray.restaurantapi.models.enums.OrderStatus;
import ru.thendray.restaurantapi.repositories.OrderRepository;

import java.util.EventListener;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;


@Component
public class OrderEventHandler implements EventListener {

    private final OrderRepository orderRepository;

    public OrderEventHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void handleEvent(OrderEvent event) {

        long randomDelay = ThreadLocalRandom.current().nextLong(1000, 10001);
        Optional<OrderEntity> order = orderRepository.findById(event.getOrderId());
        if (order.isEmpty()) {
            return;
        }
        order.get().setStatus(OrderStatus.TAKEN);
        orderRepository.saveAndFlush(order.get());
        try {
            TimeUnit.MILLISECONDS.sleep(randomDelay);
            order.get().setStatus(OrderStatus.READY);
            orderRepository.saveAndFlush(order.get());

        } catch (InterruptedException e) {
            order.get().setStatus(OrderStatus.CANCELLED);
            orderRepository.saveAndFlush(order.get());
        }

    }

}