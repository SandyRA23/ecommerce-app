package com.sra.order.service.messaging.mapper;

import com.sra.kafka.order.avro.model.*;
import com.sra.order.service.domain.dto.message.PaymentResponse;
import com.sra.order.service.domain.entity.Order;
import com.sra.order.service.domain.event.OrderCancelledEvent;
import com.sra.order.service.domain.event.OrderCreatedEvent;
import com.sra.order.service.domain.event.OrderPaidEvent;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderMessagingDataMapper {

//    public PaymentRequestAvroModel orderCreatedEventToPaymentRequestAvroModel(OrderCreatedEvent orderCreatedEvent) {
//        Order order = orderCreatedEvent.getOrder();
//        return PaymentRequestAvroModel.newBuilder()
//                .setId(UUID.randomUUID().toString())
//                .setCustomerId(order.getCustomerId().getValue().toString())
//                .setOrderId(order.getId().getValue().toString())
//                .setPrice(order.getPrice().getAmount())
//                .setCreatedAt(orderCreatedEvent.getCreatedAt().toInstant())
//                .setPaymentOrderStatus(PaymentOrderStatus.PENDING)
//                .build();
//    }
//
//    public PaymentRequestAvroModel orderCancelledEventToPaymentRequestAvroModel(OrderCancelledEvent orderCancelledEvent) {
//        Order order = orderCancelledEvent.getOrder();
//        return PaymentRequestAvroModel.newBuilder()
//                .setId(UUID.randomUUID().toString())
//                .setSagaId("")
//                .setCustomerId(order.getCustomerId().getValue().toString())
//                .setOrderId(order.getId().getValue().toString())
//                .setPrice(order.getPrice().getAmount())
//                .setCreatedAt(orderCancelledEvent.getCreatedAt().toInstant())
//                .setPaymentOrderStatus(PaymentOrderStatus.CANCELLED)
//                .build();
//    }
//
//    public PaymentResponse paymentResponseAvroModelToPaymentResponse(PaymentResponseAvroModel
//                                                                             paymentResponseAvroModel) {
//        return PaymentResponse.builder()
//                .id(paymentResponseAvroModel.getId())
//                .paymentId(paymentResponseAvroModel.getPaymentId())
//                .customerId(paymentResponseAvroModel.getCustomerId())
//                .orderId(paymentResponseAvroModel.getOrderId())
//                .price(paymentResponseAvroModel.getPrice())
//                .createdAt(paymentResponseAvroModel.getCreatedAt())
//                .paymentStatus(com.sra.domain.valueobject.PaymentStatus.valueOf(
//                        paymentResponseAvroModel.getPaymentStatus().name()))
//                .failureMessage(paymentResponseAvroModel.getFailureMessages())
//                .build();
//    }

}