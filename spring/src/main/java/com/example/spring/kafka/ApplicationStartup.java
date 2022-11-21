package com.example.spring.kafka;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@AllArgsConstructor
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    private final KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Set<String> listenerContainerIds = kafkaListenerEndpointRegistry.getListenerContainerIds();
        for (String listenerContainerId : listenerContainerIds) {
                kafkaListenerEndpointRegistry.getListenerContainer(listenerContainerId).start();
            }
        }
}
