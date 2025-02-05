package com.sra.warehouse.service.messaging.listener.kafka;

import com.sra.warehouse.service.domain.ports.input.message.listener.inventory.ProductMutationResponseMessageListener;
import com.sra.warehouse.service.messaging.mapper.WarehouseMessagingDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ProductMutationResponseKafkaListener implements KafkaConsumer<ProductMutationResponseAvroModel> {

    private final ProductMutationResponseMessageListener productMutationResponseMessageListener;
    private final WarehouseMessagingDataMapper warehouseMessagingDataMapper;

    public ProductMutationResponseKafkaListener(ProductMutationResponseMessageListener productMutationResponseMessageListener,
                                                WarehouseMessagingDataMapper warehouseMessagingDataMapper) {
        this.productMutationResponseMessageListener = productMutationResponseMessageListener;
        this.warehouseMessagingDataMapper = warehouseMessagingDataMapper;
    }

    @Override
    @KafkaListener(id = "${kafka-consumer-config.product-mutation-consumer-group-id}",
            topics = "${warehouse-service.product-mutation-response-topic-name}")
    public void receive(@Payload List<ProductMutationResponseAvroModel> messages,
                        @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        log.info("{} number of product mutation responses received with keys:{}, partitions:{} and offsets: {}",
                messages.size(),
                keys.toString(),
                partitions.toString(),
                offsets.toString());

        messages.forEach(productMutationResponseAvroModel -> {
            if (productMutationResponseAvroModel.getMutationStatus().equals("SUCCESS")) {
                log.info("Processing successful product mutation for productId: {} in warehouseId: {}",
                        productMutationResponseAvroModel.getProductId(),
                        productMutationResponseAvroModel.getWarehouseId());
                productMutationResponseMessageListener.productMutationSuccess(
                        warehouseMessagingDataMapper.productMutationResponseAvroModelToProductMutationResponse(productMutationResponseAvroModel)
                );
            } else if (productMutationResponseAvroModel.getMutationStatus().equals("FAILED")) {
                log.info("Processing failed product mutation for productId: {} in warehouseId: {}",
                        productMutationResponseAvroModel.getProductId(),
                        productMutationResponseAvroModel.getWarehouseId());
                productMutationResponseMessageListener.productMutationFailed(
                        warehouseMessagingDataMapper.productMutationResponseAvroModelToProductMutationResponse(productMutationResponseAvroModel)
                );
            }
        });
    }
}
