package com.sra.order.service.dataaccess.customer.mapper;


import com.sra.domain.valueobject.CustomerId;
import com.sra.order.service.dataaccess.customer.entity.CustomerEntity;
import com.sra.order.service.domain.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerDataAccessMapper {

    public Customer customerEntityToCustomer(CustomerEntity customerEntity) {
        return new Customer(new CustomerId(customerEntity.getId()));
    }
}