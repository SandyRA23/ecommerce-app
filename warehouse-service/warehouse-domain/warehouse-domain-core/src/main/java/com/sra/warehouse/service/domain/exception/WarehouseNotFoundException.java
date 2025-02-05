package com.sra.warehouse.service.domain.exception;

import com.sra.domain.exception.DomainException;

public class WarehouseNotFoundException extends DomainException {
    public WarehouseNotFoundException(String message) {
        super(message);
    }
    public WarehouseNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
}
