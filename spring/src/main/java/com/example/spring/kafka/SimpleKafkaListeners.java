package com.example.spring.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;

import java.util.List;

@Configuration
@AllArgsConstructor
@Log4j2
public class SimpleKafkaListeners {

    private final ObjectMapper objectMapper;

    //    @KafkaListener(id = Topic.SHOP_V1, topics = {Topic.SHOP_V1, Topic.SHOP_V2}, groupId = "CHIEN", containerFactory = "kafkaBatchListenerContainerFactory")
    @KafkaListener(id = "chien", topics = {Topic.SHOP_V1}, groupId = "PHONG", containerFactory = "kafkaBatchListenerContainerFactory")
    public void listenTripOrder(List<String> messages, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions) {
        log.info("chien");
        for (int i = 0; i < messages.size(); i++) {
            String message = messages.get(i);
            log.info(" GROUP {}: {}", partitions.get(i), message);
        }
    }

    @KafkaListener(id = "phong", topics = {Topic.SHOP_V1}, groupId = "CHIEN", containerFactory = "kafkaBatchListenerContainerFactory")
    public void listenTripOrder1(List<String> messages, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions) {
        log.info("phong");
        for (int i = 0; i < messages.size(); i++) {
            String message = messages.get(i);
            log.info(" GROUP {}: {}", partitions.get(i), message);
        }
    }
}