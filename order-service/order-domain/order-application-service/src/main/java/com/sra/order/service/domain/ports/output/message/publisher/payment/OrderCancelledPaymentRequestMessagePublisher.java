package com.sra.order.service.domain.ports.output.message.publisher.payment;

import com.sra.domain.event.publisher.DomainEventPublisher;
import com.sra.order.service.domain.event.OrderCancelledEvent;

public interface OrderCancelledPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCancelledEvent> {
}
