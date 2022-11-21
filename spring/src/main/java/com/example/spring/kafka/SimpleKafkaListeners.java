package com.example.spring.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;

import java.io.IOException;
import java.util.List;

@Configuration
@AllArgsConstructor
@Log4j2
public class SimpleKafkaListeners {

    private final ObjectMapper objectMapper;

    @KafkaListener(id = Topic.SHOP_V1, topics = Topic.SHOP_V1, containerFactory = "kafkaBatchListenerContainerFactory")
    public void listenTripOrder(List<String> messages, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions) {

        for (int i = 0; i < messages.size(); i++) {
            String message = messages.get(i);
            log.info(" GROUP {}: {}", partitions.get(i), message);
        }

    }
}
