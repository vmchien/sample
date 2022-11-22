package com.example.spring.handler;

import com.example.spring.kafka.SimpleKafkaProducerService;
import com.example.spring.kafka.Topic;
import com.example.spring.service.EmployeeWebClient;
import com.example.spring.model.Greeting;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@Component
@AllArgsConstructor
@Log4j2
public class GreetingHandler {

    public final EmployeeWebClient client;
    public final SimpleKafkaProducerService kafkaProducerService;

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

    public Mono<ServerResponse> hola(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(client.getEmployeeMono()));
    }
}