package com.example.spring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

    // Cấu hình Task Executor
    @Override
    @Bean
    public ThreadPoolTaskExecutor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5); // Số luồng cố định trong pool
        executor.setMaxPoolSize(10); // Số luồng tối đa trong pool
        executor.setQueueCapacity(25); // Số tác vụ được đợi trong hàng đợi
        executor.setThreadNamePrefix("asyncExecutor-chien");
        executor.initialize();
        return executor;
    }
}