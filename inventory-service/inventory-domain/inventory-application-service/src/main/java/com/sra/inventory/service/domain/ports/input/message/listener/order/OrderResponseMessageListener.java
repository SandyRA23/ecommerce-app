package com.sra.inventory.service.domain.ports.input.message.listener.order;

import com.sra.inventory.service.domain.dto.message.OrderResponse;
import com.sra.inventory.service.domain.entity.InventoryItem;

import java.util.List;

public interface OrderResponseMessageListener {
    void listenAutoStockMutation(OrderResponse orderResponse);
}
