package com.sra.inventory.service.domain;

import com.sra.inventory.service.domain.dto.message.OrderResponse;
import com.sra.inventory.service.domain.ports.input.message.listener.order.OrderResponseMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static com.sra.inventory.service.domain.entity.Inventory.FAILURE_MESSAGE_DELIMITER;

@Validated
@Service
@Slf4j
public class OrderResponseMessageListenerImpl implements OrderResponseMessageListener {

    public void listenAutoStockMutation(OrderResponse orderResponse) {
        log.info("Publishing AutoStockMutation for product id: {}", orderResponse.getProductId());
    }
}
