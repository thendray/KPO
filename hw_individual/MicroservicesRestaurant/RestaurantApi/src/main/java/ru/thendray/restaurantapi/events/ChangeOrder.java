package ru.thendray.restaurantapi.events;

import ru.thendray.restaurantapi.entities.OrderEntity;

import java.time.LocalDateTime;

public class ChangeOrder {

//    public OrderEntity CreateOrder(Long userId, String addInfo) {
//        OrderEntity order = new OrderEntity(null,
//                userId,
//                "Pending",
//                addInfo,
//                LocalDateTime.now(),
//                LocalDateTime.now();
//
//        orderRepository.saveAndFlush(order);
//        Thread eventThread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                OrderEvent event = new OrderEvent(this, order.getId());
//                listener.handleEvent(event);
//            }
//        });
//        eventThread.start();
//        return order;
//    }
//
}
