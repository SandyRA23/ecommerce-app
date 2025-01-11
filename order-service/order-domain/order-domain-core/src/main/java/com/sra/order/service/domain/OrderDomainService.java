package com.sra.order.service.domain;

import com.sra.domain.event.publisher.DomainEventPublisher;
import com.sra.order.service.domain.entity.Order;
import com.sra.order.service.domain.event.OrderCancelledEvent;
import com.sra.order.service.domain.event.OrderCreatedEvent;
import com.sra.order.service.domain.event.OrderPaidEvent;

import java.util.List;

public interface OrderDomainService {
    OrderCreatedEvent validateAndInitiateOrder(Order order,
                                               DomainEventPublisher<OrderCreatedEvent> orderCreatedEventDomainEventPublisher);

    OrderPaidEvent payOrder(Order order, DomainEventPublisher<OrderPaidEvent> orderPaidEventDomainEventPublisher);

    void approveOrder(Order order);

    OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages, DomainEventPublisher<OrderCancelledEvent> orderCancelledEventDomainEventPublisher);

    void cancelOrder(Order order, List<String> failureMessages);
}
