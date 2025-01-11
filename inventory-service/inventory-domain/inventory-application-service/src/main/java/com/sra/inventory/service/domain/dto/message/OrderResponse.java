package com.sra.inventory.service.domain.dto.message;

import com.sra.domain.valueobject.OrderApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class OrderResponse {
    private final String id;

    private final String customerId;

    private final String productId;

    private final String warehouseId;

    private final Instant createdAt;

    private final OrderApprovalStatus orderApprovalStatus;

    private final List<String> failureMessage;
}
