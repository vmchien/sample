package com.example.spring.handler;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class TestService {


    @Async
    public void ex(){
        try {
            Thread.sleep(5000);
            System.out.println("chien");
        }catch (Exception e){
        }
    }
    @Async
    public void ex(int a){
        try {
            Thread.sleep(4000);
            System.out.println("nam");
        }catch (Exception e){
        }
    }
}
