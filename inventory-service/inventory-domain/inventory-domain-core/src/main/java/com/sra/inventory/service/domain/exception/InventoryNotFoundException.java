package com.sra.inventory.service.domain.exception;

import com.sra.domain.exception.DomainException;

public class InventoryNotFoundException extends DomainException {
    public InventoryNotFoundException(String message) {
        super(message);
    }
    public InventoryNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
}