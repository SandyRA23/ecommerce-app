package com.sra.inventory.service.domain.entity;

import com.sra.domain.entity.AggregateRoot;
import com.sra.domain.valueobject.OrderId;

public class Order extends AggregateRoot<OrderId> {
    public Order() {
    }

    public Order(OrderId orderId) {
        super.setId(orderId);
    }
}
