package com.example.spring.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.kafka.common.errors.TimeoutException;

import java.util.concurrent.ExecutionException;

public interface KafkaProducerService {

    <T> void sendMessage(String topic, String key, T t) throws JsonProcessingException, InterruptedException, ExecutionException, TimeoutException, java.util.concurrent.TimeoutException;

    <T> void sendMessage(String topic, T t, int partition) throws JsonProcessingException, InterruptedException, ExecutionException, TimeoutException, java.util.concurrent.TimeoutException;

    <T> void sendMessage(String topic, T t) throws JsonProcessingException, InterruptedException, ExecutionException, TimeoutException, java.util.concurrent.TimeoutException;

}