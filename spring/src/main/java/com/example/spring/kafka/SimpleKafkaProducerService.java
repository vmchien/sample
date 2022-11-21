package com.example.spring.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
public class SimpleKafkaProducerService implements KafkaProducerService {

    private long count = 0;

    private final ObjectMapper objectMapper;

    @Value("${kafka.producer.enable}")
    private boolean enable;

    @Autowired
    public SimpleKafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = new ObjectMapper();
    }

    private final KafkaTemplate<String, String> kafkaTemplate;

    private void sendMessage(String topic, String key, String msg) throws ExecutionException, InterruptedException, TimeoutException {
        kafkaTemplate.send(topic, key, msg).get(15, TimeUnit.SECONDS);
    }

    private void sendMessage(String topic, long key, String msg, int partition) throws ExecutionException, InterruptedException, TimeoutException {
        kafkaTemplate.send(topic, partition, String.valueOf(key), msg).get(15, TimeUnit.SECONDS);
    }

    private void sendMessage(String topic, long key, String msg) throws ExecutionException, InterruptedException, TimeoutException {
        kafkaTemplate.send(topic, String.valueOf(key), msg).get(15, TimeUnit.SECONDS);
    }

    public <T> void sendMessage(String topic, String key, T t) throws JsonProcessingException, InterruptedException, ExecutionException, TimeoutException {
        if (enable) {
            String message = objectMapper.writeValueAsString(t);
            sendMessage(topic, key, message);
        }
    }

    public <T> void sendMessage(String topic, T t, int partition) throws JsonProcessingException, InterruptedException, ExecutionException, TimeoutException {
        if (enable) {
            count++;
            String message = objectMapper.writeValueAsString(t);
            sendMessage(topic, count, message, partition);
        }
    }

    @Override
    public <T> void sendMessage(String topic, T t) throws JsonProcessingException, InterruptedException, ExecutionException, TimeoutException {
        if (enable) {
            count++;
            String message = objectMapper.writeValueAsString(t);
            sendMessage(topic, count, message);
        }
    }
}