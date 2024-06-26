package com.example.spring.router;

import com.example.spring.handler.GreetingHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration(proxyBeanMethods = false)
public class GreetingRouter {

    @Bean
    public RouterFunction<ServerResponse> route(GreetingHandler greetingHandler) {
        return RouterFunctions
                .route(GET("/hello").and(accept(MediaType.APPLICATION_JSON)), greetingHandler::sendKafka)
                .andRoute(GET("/hola").and(accept(MediaType.APPLICATION_JSON)), greetingHandler::sendKafka1)
                .andRoute(GET("/asd").and(accept(MediaType.APPLICATION_JSON)), greetingHandler::messageOld)
                .andRoute(GET("/test").and(accept(MediaType.APPLICATION_JSON)), greetingHandler::test);
    }
}