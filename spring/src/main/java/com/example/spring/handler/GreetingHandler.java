package com.example.spring.handler;

import com.example.spring.kafka.SimpleKafkaProducerService;
import com.example.spring.kafka.Topic;
import com.example.spring.kafka.config.KafkaConsumerConfig;
import com.example.spring.service.EmployeeWebClient;
import com.example.spring.model.Greeting;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.TopicPartition;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.HashMap;

@Component
@AllArgsConstructor
@Log4j2
public class GreetingHandler {

    public final EmployeeWebClient client;
    public final SimpleKafkaProducerService kafkaProducerService;
    public final KafkaConsumerConfig kafkaConsumerConfig;

    public Mono<ServerResponse> sendKafka(ServerRequest request) {
        try {
            log.info("send kafka");
            for (int i = 0; i < 10; i++) {
                kafkaProducerService.sendMessage(Topic.SHOP_V1, i);
            }
            return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(new Greeting("Hello, Spring!")));
        } catch (Exception e) {
            return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(new Greeting("Hello, ERROR!")));
        }
    }

    public Mono<ServerResponse> sendKafka1(ServerRequest request) {
        try {
            log.info("send kafka 1");
            for (int i = 0; i < 10; i++) {
                kafkaProducerService.sendMessage(Topic.SHOP_V2,  "C: "+i);
            }
            return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(new Greeting("Hello, Spring!")));
        } catch (Exception e) {
            return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(new Greeting("Hello, ERROR!")));
        }
    }

    public Mono<ServerResponse> messageOld(ServerRequest request) {
        var consumer = kafkaConsumerConfig.getConsumer();
        var topic = Topic.SHOP_V1;
        TopicPartition partition = new TopicPartition(topic, 0); // Thay thế 0 bằng partition muốn lấy tin nhắn
        long offset = 1000; // Thay thế bằng offset muốn bắt đầu lấy tin nhắn
        consumer.assign(Collections.singleton(partition));
        consumer.seek(partition, offset);

        log.info("LOGS: ");
        var result = new HashMap<>();
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(5);
            records.forEach(record -> {
                log.info("Key: " + record.key() + ", Value: " + record.value());
                result.put(record.key(),record.value());
            });

            // Kiểm tra nếu không còn tin nhắn nào để lấy, thoát khỏi vòng lặp
            if (records.isEmpty()) {
                break;
            }
        }
        consumer.close();

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(result));
    }
}