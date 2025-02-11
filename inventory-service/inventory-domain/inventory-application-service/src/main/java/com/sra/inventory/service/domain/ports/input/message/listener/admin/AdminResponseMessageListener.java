package com.sra.inventory.service.domain.ports.input.message.listener.admin;

import com.sra.inventory.service.domain.dto.create.CreateInventoryCommand;

import java.util.List;

public interface AdminResponseMessageListener {
    void listenInventoryCreation(CreateInventoryCommand createInventoryCommand);
    void listenManualStockMutation(String mutationId, List<InventoryItem> inventoryItems);
}
