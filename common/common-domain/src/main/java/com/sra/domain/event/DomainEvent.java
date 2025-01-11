package com.sra.domain.event;

public interface DomainEvent<T> {
    void fire();
}
