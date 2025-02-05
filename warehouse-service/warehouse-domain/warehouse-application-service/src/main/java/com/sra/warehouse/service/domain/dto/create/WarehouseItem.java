package com.sra.warehouse.service.domain.dto.create;

import com.sra.domain.valueobject.ProductId;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class WarehouseItem {
    @NotNull
    private final ProductId productId;
    @NotNull
    private final int quantity;
}
