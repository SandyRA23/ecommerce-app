package com.sra.order.service.domain.ports.output.repository;

import com.sra.domain.valueobject.OrderId;
import com.sra.order.service.domain.entity.Order;
import com.sra.order.service.domain.valueobject.TrackingId;

import java.util.Optional;

public interface OrderRepository {

    Order save(Order order);

    Optional<Order> findById(OrderId orderId);

    Optional<Order> findByTrackingId(TrackingId trackingId);
}