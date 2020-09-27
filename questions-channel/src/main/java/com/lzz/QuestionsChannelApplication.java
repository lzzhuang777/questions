package com.lzz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication
public class QuestionsChannelApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuestionsChannelApplication.class, args);
    }

}
