package com.example.spring.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

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

    @KafkaListener(id = "nam", topics = {Topic.SHOP_V1}, groupId = "CHIEN", containerFactory = "kafkaBatchListenerContainerFactory")
    public void listenTripOrder2(@Payload String messages, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) Integer partitions) {
        log.info("nam");
        log.info(" GROUP {}: {}", partitions, messages);
    }

    @KafkaListener(id = "minh", topics = {Topic.SHOP_V1}, groupId = "CHIEN", containerFactory = "kafkaBatchListenerContainerFactory")
    public void listenTripOrder3(List<String> messages, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions) {
        log.info("minh");
        for (int i = 0; i < messages.size(); i++) {
            String message = messages.get(i);
            log.info(" GROUP {}: {}", partitions.get(i), message);
        }
    }

    @KafkaListener(id = "h  uong", topics = {Topic.SHOP_V1}, groupId = "CHIEN", containerFactory = "kafkaBatchListenerContainerFactory")
    public void listenTripOrder4(List<String> messages, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions) {
        log.info("huong");
        for (int i = 0; i < messages.size(); i++) {
            String message = messages.get(i);
            log.info(" GROUP {}: {}", partitions.get(i), message);
        }
    }

    @KafkaListener(id = "huong1", topics = {Topic.SHOP_V1}, groupId = "CHIEN", containerFactory = "kafkaBatchListenerContainerFactory")
    public void listenTripOrder5(List<String> messages, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions) {
        log.info("xxx");
        for (int i = 0; i < messages.size(); i++) {
            String message = messages.get(i);
            log.info(" GROUP {}: {}", partitions.get(i), message);
        }
    }
}