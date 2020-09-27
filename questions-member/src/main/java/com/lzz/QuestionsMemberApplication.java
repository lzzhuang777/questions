package com.lzz;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages ="com.lzz.feign")
@EnableDiscoveryClient
@SpringBootApplication
public class QuestionsMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuestionsMemberApplication.class, args);
    }

}
