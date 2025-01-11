package com.sra.inventory.service.domain.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class CreateInventoryCommand {
    @NotNull
    private final UUID warehouseId;
    @NotNull
    private final UUID productId;
    @NotNull
    private final List<InventoryItem> items;
}
