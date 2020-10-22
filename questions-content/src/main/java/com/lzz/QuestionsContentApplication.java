package com.lzz;

import co.elastic.apm.attach.ElasticApmAttacher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication
public class QuestionsContentApplication {

    public static void main(String[] args) {
        ElasticApmAttacher.attach();
        SpringApplication.run(QuestionsContentApplication.class, args);
    }

}
