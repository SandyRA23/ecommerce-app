package com.sra.warehouse.service.domain.exception;

import com.sra.domain.exception.DomainException;

public class WarehouseDomainException extends DomainException {

    public WarehouseDomainException(String message) {
        super(message);
    }

    public WarehouseDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
