package com.sra.inventory.service.domain.ports.input.message.listener.admin;

import com.sra.inventory.service.domain.dto.create.CreateInventoryCommand;
import com.sra.inventory.service.domain.entity.InventoryItem;

import java.util.List;

public interface AdminResponseMessageListener {
    void listenInventoryCreation(CreateInventoryCommand createInventoryCommand);
    void listenManualStockMutation(String mutationId, List<InventoryItem> inventoryItems);
}
