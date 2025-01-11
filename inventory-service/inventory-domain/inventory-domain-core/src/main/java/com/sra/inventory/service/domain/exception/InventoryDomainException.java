package com.sra.inventory.service.domain.exception;

import com.sra.domain.exception.DomainException;

public class InventoryDomainException extends DomainException {

    public InventoryDomainException(String message) {
        super(message);
    }

    public InventoryDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
