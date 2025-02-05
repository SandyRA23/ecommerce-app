package com.sra.warehouse.service.domain.dto.create;

import com.sra.warehouse.service.domain.valueobject.Capacity;
import com.sra.warehouse.service.domain.valueobject.Location;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class CreateWarehouseCommand {
    @NotNull
    private final String name;
    @NotNull
    private final Location location;
    @NotNull
    private final List<WarehouseItem> items;
    @NotNull
    private final Capacity capacity;
}
