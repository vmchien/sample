package com.example.spring.service;

import com.example.spring.configuration.NsWebClient;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@Log4j2
@AllArgsConstructor
public class EmployeeWebClient {

    private final NsWebClient client;


    public Mono<Object> getEmployeeMono() {
        WebClient webClient = WebClient.create("https://api.coindesk.com/v1/bpi/currentprice.json");
        return webClient.get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Object.class);
    }
}
