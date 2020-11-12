package com.lzz;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication(scanBasePackages = {"com.lzz","com.lzz.exception"})
@EnableDiscoveryClient
public class QuestionsGatewayApplication {


    public static void main(String[] args) {
        //ElasticApmAttacher.attach();
        SpringApplication.run(QuestionsGatewayApplication.class, args);
    }

}
