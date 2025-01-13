package com.sra.inventory.service.domain;

import com.sra.inventory.service.domain.ports.output.message.publisher.InventoryMessagePublisher;
import com.sra.inventory.service.domain.ports.output.repository.InventoryRepository;
import com.sra.inventory.service.domain.ports.output.repository.ProductRepository;
import com.sra.inventory.service.domain.ports.output.repository.WarehouseRepository;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.sra")
public class InventoryTestConfiguration {
    @Bean
    public InventoryMessagePublisher inventoryMessagePublisher(){
        return Mockito.mock(InventoryMessagePublisher.class);
    }

    @Bean
    public InventoryRepository inventoryRepository(){
        return Mockito.mock(InventoryRepository.class);
    }

    @Bean
    public WarehouseRepository warehouseRepository(){
        return Mockito.mock(WarehouseRepository.class);
    }

    @Bean
    public ProductRepository productRepository(){
        return Mockito.mock(ProductRepository.class);
    }

    @Bean
    public InventoryDomainService inventoryDomainService(){
        return new InventoryDomainServiceImpl();
    }
}
