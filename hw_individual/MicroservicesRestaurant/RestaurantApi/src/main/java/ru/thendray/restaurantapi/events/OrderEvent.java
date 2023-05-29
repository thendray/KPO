package ru.thendray.restaurantapi.events;

import java.util.EventObject;

public class OrderEvent extends EventObject {
    private final Long orderId;

    public Long getOrderId() {
        return orderId;
    }

    public OrderEvent(Object source, Long orderId) {
        super(source);
        this.orderId = orderId;
    }
}